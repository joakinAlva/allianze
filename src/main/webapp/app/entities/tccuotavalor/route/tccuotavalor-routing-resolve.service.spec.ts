jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCCUOTAVALOR, TCCUOTAVALOR } from '../tccuotavalor.model';
import { TCCUOTAVALORService } from '../service/tccuotavalor.service';

import { TCCUOTAVALORRoutingResolveService } from './tccuotavalor-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCCUOTAVALOR routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCCUOTAVALORRoutingResolveService;
    let service: TCCUOTAVALORService;
    let resultTCCUOTAVALOR: ITCCUOTAVALOR | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCCUOTAVALORRoutingResolveService);
      service = TestBed.inject(TCCUOTAVALORService);
      resultTCCUOTAVALOR = undefined;
    });

    describe('resolve', () => {
      it('should return ITCCUOTAVALOR returned by find', () => {
        // GIVEN
        service.find = jest.fn(idCuotaValor => of(new HttpResponse({ body: { idCuotaValor } })));
        mockActivatedRouteSnapshot.params = { idCuotaValor: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCUOTAVALOR = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCUOTAVALOR).toEqual({ idCuotaValor: 123 });
      });

      it('should return new ITCCUOTAVALOR if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCUOTAVALOR = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCCUOTAVALOR).toEqual(new TCCUOTAVALOR());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idCuotaValor: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCUOTAVALOR = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCUOTAVALOR).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
