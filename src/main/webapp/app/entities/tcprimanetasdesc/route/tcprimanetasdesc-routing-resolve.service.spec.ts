jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCPRIMANETASDESC, TCPRIMANETASDESC } from '../tcprimanetasdesc.model';
import { TCPRIMANETASDESCService } from '../service/tcprimanetasdesc.service';

import { TCPRIMANETASDESCRoutingResolveService } from './tcprimanetasdesc-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCPRIMANETASDESC routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCPRIMANETASDESCRoutingResolveService;
    let service: TCPRIMANETASDESCService;
    let resultTCPRIMANETASDESC: ITCPRIMANETASDESC | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCPRIMANETASDESCRoutingResolveService);
      service = TestBed.inject(TCPRIMANETASDESCService);
      resultTCPRIMANETASDESC = undefined;
    });

    describe('resolve', () => {
      it('should return ITCPRIMANETASDESC returned by find', () => {
        // GIVEN
        service.find = jest.fn(idPrimaNetaSdesc => of(new HttpResponse({ body: { idPrimaNetaSdesc } })));
        mockActivatedRouteSnapshot.params = { idPrimaNetaSdesc: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCPRIMANETASDESC = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCPRIMANETASDESC).toEqual({ idPrimaNetaSdesc: 123 });
      });

      it('should return new ITCPRIMANETASDESC if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCPRIMANETASDESC = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCPRIMANETASDESC).toEqual(new TCPRIMANETASDESC());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idPrimaNetaSdesc: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCPRIMANETASDESC = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCPRIMANETASDESC).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
