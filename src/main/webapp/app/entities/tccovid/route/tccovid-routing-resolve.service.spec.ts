jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCCOVID, TCCOVID } from '../tccovid.model';
import { TCCOVIDService } from '../service/tccovid.service';

import { TCCOVIDRoutingResolveService } from './tccovid-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCCOVID routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCCOVIDRoutingResolveService;
    let service: TCCOVIDService;
    let resultTCCOVID: ITCCOVID | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCCOVIDRoutingResolveService);
      service = TestBed.inject(TCCOVIDService);
      resultTCCOVID = undefined;
    });

    describe('resolve', () => {
      it('should return ITCCOVID returned by find', () => {
        // GIVEN
        service.find = jest.fn(idCovid => of(new HttpResponse({ body: { idCovid } })));
        mockActivatedRouteSnapshot.params = { idCovid: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCOVID = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCOVID).toEqual({ idCovid: 123 });
      });

      it('should return new ITCCOVID if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCOVID = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCCOVID).toEqual(new TCCOVID());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idCovid: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCOVID = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCOVID).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
