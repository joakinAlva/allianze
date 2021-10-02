jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCSUMAASEGURADA, TCSUMAASEGURADA } from '../tcsumaasegurada.model';
import { TCSUMAASEGURADAService } from '../service/tcsumaasegurada.service';

import { TCSUMAASEGURADARoutingResolveService } from './tcsumaasegurada-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCSUMAASEGURADA routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCSUMAASEGURADARoutingResolveService;
    let service: TCSUMAASEGURADAService;
    let resultTCSUMAASEGURADA: ITCSUMAASEGURADA | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCSUMAASEGURADARoutingResolveService);
      service = TestBed.inject(TCSUMAASEGURADAService);
      resultTCSUMAASEGURADA = undefined;
    });

    describe('resolve', () => {
      it('should return ITCSUMAASEGURADA returned by find', () => {
        // GIVEN
        service.find = jest.fn(idSumaAsegurada => of(new HttpResponse({ body: { idSumaAsegurada } })));
        mockActivatedRouteSnapshot.params = { idSumaAsegurada: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCSUMAASEGURADA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCSUMAASEGURADA).toEqual({ idSumaAsegurada: 123 });
      });

      it('should return new ITCSUMAASEGURADA if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCSUMAASEGURADA = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCSUMAASEGURADA).toEqual(new TCSUMAASEGURADA());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idSumaAsegurada: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCSUMAASEGURADA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCSUMAASEGURADA).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
