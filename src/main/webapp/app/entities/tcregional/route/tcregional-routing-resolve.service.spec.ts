jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCREGIONAL, TCREGIONAL } from '../tcregional.model';
import { TCREGIONALService } from '../service/tcregional.service';

import { TCREGIONALRoutingResolveService } from './tcregional-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCREGIONAL routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCREGIONALRoutingResolveService;
    let service: TCREGIONALService;
    let resultTCREGIONAL: ITCREGIONAL | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCREGIONALRoutingResolveService);
      service = TestBed.inject(TCREGIONALService);
      resultTCREGIONAL = undefined;
    });

    describe('resolve', () => {
      it('should return ITCREGIONAL returned by find', () => {
        // GIVEN
        service.find = jest.fn(idRegional => of(new HttpResponse({ body: { idRegional } })));
        mockActivatedRouteSnapshot.params = { idRegional: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCREGIONAL = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCREGIONAL).toEqual({ idRegional: 123 });
      });

      it('should return new ITCREGIONAL if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCREGIONAL = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCREGIONAL).toEqual(new TCREGIONAL());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idRegional: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCREGIONAL = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCREGIONAL).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
