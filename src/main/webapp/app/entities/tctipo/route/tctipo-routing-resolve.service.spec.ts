jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCTIPO, TCTIPO } from '../tctipo.model';
import { TCTIPOService } from '../service/tctipo.service';

import { TCTIPORoutingResolveService } from './tctipo-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCTIPO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCTIPORoutingResolveService;
    let service: TCTIPOService;
    let resultTCTIPO: ITCTIPO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCTIPORoutingResolveService);
      service = TestBed.inject(TCTIPOService);
      resultTCTIPO = undefined;
    });

    describe('resolve', () => {
      it('should return ITCTIPO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idTipo => of(new HttpResponse({ body: { idTipo } })));
        mockActivatedRouteSnapshot.params = { idTipo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCTIPO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCTIPO).toEqual({ idTipo: 123 });
      });

      it('should return new ITCTIPO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCTIPO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCTIPO).toEqual(new TCTIPO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idTipo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCTIPO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCTIPO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
