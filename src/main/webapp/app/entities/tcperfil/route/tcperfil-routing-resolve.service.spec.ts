jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCPERFIL, TCPERFIL } from '../tcperfil.model';
import { TCPERFILService } from '../service/tcperfil.service';

import { TCPERFILRoutingResolveService } from './tcperfil-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCPERFIL routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCPERFILRoutingResolveService;
    let service: TCPERFILService;
    let resultTCPERFIL: ITCPERFIL | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCPERFILRoutingResolveService);
      service = TestBed.inject(TCPERFILService);
      resultTCPERFIL = undefined;
    });

    describe('resolve', () => {
      it('should return ITCPERFIL returned by find', () => {
        // GIVEN
        service.find = jest.fn(idPerfil => of(new HttpResponse({ body: { idPerfil } })));
        mockActivatedRouteSnapshot.params = { idPerfil: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCPERFIL = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCPERFIL).toEqual({ idPerfil: 123 });
      });

      it('should return new ITCPERFIL if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCPERFIL = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCPERFIL).toEqual(new TCPERFIL());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idPerfil: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCPERFIL = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCPERFIL).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
