jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCPRIMATARIFA, TCPRIMATARIFA } from '../tcprimatarifa.model';
import { TCPRIMATARIFAService } from '../service/tcprimatarifa.service';

import { TCPRIMATARIFARoutingResolveService } from './tcprimatarifa-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCPRIMATARIFA routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCPRIMATARIFARoutingResolveService;
    let service: TCPRIMATARIFAService;
    let resultTCPRIMATARIFA: ITCPRIMATARIFA | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCPRIMATARIFARoutingResolveService);
      service = TestBed.inject(TCPRIMATARIFAService);
      resultTCPRIMATARIFA = undefined;
    });

    describe('resolve', () => {
      it('should return ITCPRIMATARIFA returned by find', () => {
        // GIVEN
        service.find = jest.fn(idPrimaTarifa => of(new HttpResponse({ body: { idPrimaTarifa } })));
        mockActivatedRouteSnapshot.params = { idPrimaTarifa: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCPRIMATARIFA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCPRIMATARIFA).toEqual({ idPrimaTarifa: 123 });
      });

      it('should return new ITCPRIMATARIFA if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCPRIMATARIFA = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCPRIMATARIFA).toEqual(new TCPRIMATARIFA());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idPrimaTarifa: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCPRIMATARIFA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCPRIMATARIFA).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
