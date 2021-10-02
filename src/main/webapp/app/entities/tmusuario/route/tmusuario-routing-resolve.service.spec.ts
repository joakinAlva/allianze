jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITMUSUARIO, TMUSUARIO } from '../tmusuario.model';
import { TMUSUARIOService } from '../service/tmusuario.service';

import { TMUSUARIORoutingResolveService } from './tmusuario-routing-resolve.service';

describe('Service Tests', () => {
  describe('TMUSUARIO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TMUSUARIORoutingResolveService;
    let service: TMUSUARIOService;
    let resultTMUSUARIO: ITMUSUARIO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TMUSUARIORoutingResolveService);
      service = TestBed.inject(TMUSUARIOService);
      resultTMUSUARIO = undefined;
    });

    describe('resolve', () => {
      it('should return ITMUSUARIO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idUsuario => of(new HttpResponse({ body: { idUsuario } })));
        mockActivatedRouteSnapshot.params = { idUsuario: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMUSUARIO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTMUSUARIO).toEqual({ idUsuario: 123 });
      });

      it('should return new ITMUSUARIO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMUSUARIO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTMUSUARIO).toEqual(new TMUSUARIO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idUsuario: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMUSUARIO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTMUSUARIO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
