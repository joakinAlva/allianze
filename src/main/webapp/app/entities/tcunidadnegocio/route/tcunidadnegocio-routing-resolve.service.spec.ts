jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCUNIDADNEGOCIO, TCUNIDADNEGOCIO } from '../tcunidadnegocio.model';
import { TCUNIDADNEGOCIOService } from '../service/tcunidadnegocio.service';

import { TCUNIDADNEGOCIORoutingResolveService } from './tcunidadnegocio-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCUNIDADNEGOCIO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCUNIDADNEGOCIORoutingResolveService;
    let service: TCUNIDADNEGOCIOService;
    let resultTCUNIDADNEGOCIO: ITCUNIDADNEGOCIO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCUNIDADNEGOCIORoutingResolveService);
      service = TestBed.inject(TCUNIDADNEGOCIOService);
      resultTCUNIDADNEGOCIO = undefined;
    });

    describe('resolve', () => {
      it('should return ITCUNIDADNEGOCIO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idUnidadNegocio => of(new HttpResponse({ body: { idUnidadNegocio } })));
        mockActivatedRouteSnapshot.params = { idUnidadNegocio: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCUNIDADNEGOCIO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCUNIDADNEGOCIO).toEqual({ idUnidadNegocio: 123 });
      });

      it('should return new ITCUNIDADNEGOCIO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCUNIDADNEGOCIO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCUNIDADNEGOCIO).toEqual(new TCUNIDADNEGOCIO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idUnidadNegocio: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCUNIDADNEGOCIO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCUNIDADNEGOCIO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
