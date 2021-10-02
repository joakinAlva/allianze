jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCOCUPACION, TCOCUPACION } from '../tcocupacion.model';
import { TCOCUPACIONService } from '../service/tcocupacion.service';

import { TCOCUPACIONRoutingResolveService } from './tcocupacion-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCOCUPACION routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCOCUPACIONRoutingResolveService;
    let service: TCOCUPACIONService;
    let resultTCOCUPACION: ITCOCUPACION | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCOCUPACIONRoutingResolveService);
      service = TestBed.inject(TCOCUPACIONService);
      resultTCOCUPACION = undefined;
    });

    describe('resolve', () => {
      it('should return ITCOCUPACION returned by find', () => {
        // GIVEN
        service.find = jest.fn(idOcupacion => of(new HttpResponse({ body: { idOcupacion } })));
        mockActivatedRouteSnapshot.params = { idOcupacion: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCOCUPACION = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCOCUPACION).toEqual({ idOcupacion: 123 });
      });

      it('should return new ITCOCUPACION if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCOCUPACION = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCOCUPACION).toEqual(new TCOCUPACION());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idOcupacion: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCOCUPACION = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCOCUPACION).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
