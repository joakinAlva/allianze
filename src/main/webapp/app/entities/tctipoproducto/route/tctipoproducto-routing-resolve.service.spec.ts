jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCTIPOPRODUCTO, TCTIPOPRODUCTO } from '../tctipoproducto.model';
import { TCTIPOPRODUCTOService } from '../service/tctipoproducto.service';

import { TCTIPOPRODUCTORoutingResolveService } from './tctipoproducto-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCTIPOPRODUCTO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCTIPOPRODUCTORoutingResolveService;
    let service: TCTIPOPRODUCTOService;
    let resultTCTIPOPRODUCTO: ITCTIPOPRODUCTO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCTIPOPRODUCTORoutingResolveService);
      service = TestBed.inject(TCTIPOPRODUCTOService);
      resultTCTIPOPRODUCTO = undefined;
    });

    describe('resolve', () => {
      it('should return ITCTIPOPRODUCTO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idTipoProducto => of(new HttpResponse({ body: { idTipoProducto } })));
        mockActivatedRouteSnapshot.params = { idTipoProducto: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCTIPOPRODUCTO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCTIPOPRODUCTO).toEqual({ idTipoProducto: 123 });
      });

      it('should return new ITCTIPOPRODUCTO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCTIPOPRODUCTO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCTIPOPRODUCTO).toEqual(new TCTIPOPRODUCTO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idTipoProducto: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCTIPOPRODUCTO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCTIPOPRODUCTO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
