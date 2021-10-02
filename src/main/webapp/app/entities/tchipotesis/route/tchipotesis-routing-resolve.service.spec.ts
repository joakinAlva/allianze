jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCHIPOTESIS, TCHIPOTESIS } from '../tchipotesis.model';
import { TCHIPOTESISService } from '../service/tchipotesis.service';

import { TCHIPOTESISRoutingResolveService } from './tchipotesis-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCHIPOTESIS routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCHIPOTESISRoutingResolveService;
    let service: TCHIPOTESISService;
    let resultTCHIPOTESIS: ITCHIPOTESIS | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCHIPOTESISRoutingResolveService);
      service = TestBed.inject(TCHIPOTESISService);
      resultTCHIPOTESIS = undefined;
    });

    describe('resolve', () => {
      it('should return ITCHIPOTESIS returned by find', () => {
        // GIVEN
        service.find = jest.fn(idHipotesis => of(new HttpResponse({ body: { idHipotesis } })));
        mockActivatedRouteSnapshot.params = { idHipotesis: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCHIPOTESIS = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCHIPOTESIS).toEqual({ idHipotesis: 123 });
      });

      it('should return new ITCHIPOTESIS if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCHIPOTESIS = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCHIPOTESIS).toEqual(new TCHIPOTESIS());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idHipotesis: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCHIPOTESIS = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCHIPOTESIS).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
