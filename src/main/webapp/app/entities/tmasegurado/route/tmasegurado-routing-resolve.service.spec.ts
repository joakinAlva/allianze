jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITMASEGURADO, TMASEGURADO } from '../tmasegurado.model';
import { TMASEGURADOService } from '../service/tmasegurado.service';

import { TMASEGURADORoutingResolveService } from './tmasegurado-routing-resolve.service';

describe('Service Tests', () => {
  describe('TMASEGURADO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TMASEGURADORoutingResolveService;
    let service: TMASEGURADOService;
    let resultTMASEGURADO: ITMASEGURADO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TMASEGURADORoutingResolveService);
      service = TestBed.inject(TMASEGURADOService);
      resultTMASEGURADO = undefined;
    });

    describe('resolve', () => {
      it('should return ITMASEGURADO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idAsegurados => of(new HttpResponse({ body: { idAsegurados } })));
        mockActivatedRouteSnapshot.params = { idAsegurados: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMASEGURADO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTMASEGURADO).toEqual({ idAsegurados: 123 });
      });

      it('should return new ITMASEGURADO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMASEGURADO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTMASEGURADO).toEqual(new TMASEGURADO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idAsegurados: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMASEGURADO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTMASEGURADO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
