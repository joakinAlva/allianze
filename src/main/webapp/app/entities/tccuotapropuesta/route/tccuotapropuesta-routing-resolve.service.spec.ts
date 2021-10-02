jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCCUOTAPROPUESTA, TCCUOTAPROPUESTA } from '../tccuotapropuesta.model';
import { TCCUOTAPROPUESTAService } from '../service/tccuotapropuesta.service';

import { TCCUOTAPROPUESTARoutingResolveService } from './tccuotapropuesta-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCCUOTAPROPUESTA routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCCUOTAPROPUESTARoutingResolveService;
    let service: TCCUOTAPROPUESTAService;
    let resultTCCUOTAPROPUESTA: ITCCUOTAPROPUESTA | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCCUOTAPROPUESTARoutingResolveService);
      service = TestBed.inject(TCCUOTAPROPUESTAService);
      resultTCCUOTAPROPUESTA = undefined;
    });

    describe('resolve', () => {
      it('should return ITCCUOTAPROPUESTA returned by find', () => {
        // GIVEN
        service.find = jest.fn(idCuotaPropuesta => of(new HttpResponse({ body: { idCuotaPropuesta } })));
        mockActivatedRouteSnapshot.params = { idCuotaPropuesta: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCUOTAPROPUESTA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCUOTAPROPUESTA).toEqual({ idCuotaPropuesta: 123 });
      });

      it('should return new ITCCUOTAPROPUESTA if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCUOTAPROPUESTA = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCCUOTAPROPUESTA).toEqual(new TCCUOTAPROPUESTA());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idCuotaPropuesta: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCUOTAPROPUESTA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCUOTAPROPUESTA).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
