jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCCOBERTURA, TCCOBERTURA } from '../tccobertura.model';
import { TCCOBERTURAService } from '../service/tccobertura.service';

import { TCCOBERTURARoutingResolveService } from './tccobertura-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCCOBERTURA routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCCOBERTURARoutingResolveService;
    let service: TCCOBERTURAService;
    let resultTCCOBERTURA: ITCCOBERTURA | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCCOBERTURARoutingResolveService);
      service = TestBed.inject(TCCOBERTURAService);
      resultTCCOBERTURA = undefined;
    });

    describe('resolve', () => {
      it('should return ITCCOBERTURA returned by find', () => {
        // GIVEN
        service.find = jest.fn(idCobertura => of(new HttpResponse({ body: { idCobertura } })));
        mockActivatedRouteSnapshot.params = { idCobertura: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCOBERTURA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCOBERTURA).toEqual({ idCobertura: 123 });
      });

      it('should return new ITCCOBERTURA if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCOBERTURA = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCCOBERTURA).toEqual(new TCCOBERTURA());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idCobertura: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCOBERTURA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCOBERTURA).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
