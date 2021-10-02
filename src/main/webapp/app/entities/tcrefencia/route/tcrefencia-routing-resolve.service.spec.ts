jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCREFENCIA, TCREFENCIA } from '../tcrefencia.model';
import { TCREFENCIAService } from '../service/tcrefencia.service';

import { TCREFENCIARoutingResolveService } from './tcrefencia-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCREFENCIA routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCREFENCIARoutingResolveService;
    let service: TCREFENCIAService;
    let resultTCREFENCIA: ITCREFENCIA | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCREFENCIARoutingResolveService);
      service = TestBed.inject(TCREFENCIAService);
      resultTCREFENCIA = undefined;
    });

    describe('resolve', () => {
      it('should return ITCREFENCIA returned by find', () => {
        // GIVEN
        service.find = jest.fn(idReferencia => of(new HttpResponse({ body: { idReferencia } })));
        mockActivatedRouteSnapshot.params = { idReferencia: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCREFENCIA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCREFENCIA).toEqual({ idReferencia: 123 });
      });

      it('should return new ITCREFENCIA if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCREFENCIA = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCREFENCIA).toEqual(new TCREFENCIA());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idReferencia: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCREFENCIA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCREFENCIA).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
