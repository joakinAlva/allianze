jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCFACTORDESCUENTO, TCFACTORDESCUENTO } from '../tcfactordescuento.model';
import { TCFACTORDESCUENTOService } from '../service/tcfactordescuento.service';

import { TCFACTORDESCUENTORoutingResolveService } from './tcfactordescuento-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCFACTORDESCUENTO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCFACTORDESCUENTORoutingResolveService;
    let service: TCFACTORDESCUENTOService;
    let resultTCFACTORDESCUENTO: ITCFACTORDESCUENTO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCFACTORDESCUENTORoutingResolveService);
      service = TestBed.inject(TCFACTORDESCUENTOService);
      resultTCFACTORDESCUENTO = undefined;
    });

    describe('resolve', () => {
      it('should return ITCFACTORDESCUENTO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idFactorDescuento => of(new HttpResponse({ body: { idFactorDescuento } })));
        mockActivatedRouteSnapshot.params = { idFactorDescuento: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCFACTORDESCUENTO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCFACTORDESCUENTO).toEqual({ idFactorDescuento: 123 });
      });

      it('should return new ITCFACTORDESCUENTO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCFACTORDESCUENTO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCFACTORDESCUENTO).toEqual(new TCFACTORDESCUENTO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idFactorDescuento: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCFACTORDESCUENTO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCFACTORDESCUENTO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
