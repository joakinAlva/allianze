jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCFACTORSAMI, TCFACTORSAMI } from '../tcfactorsami.model';
import { TCFACTORSAMIService } from '../service/tcfactorsami.service';

import { TCFACTORSAMIRoutingResolveService } from './tcfactorsami-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCFACTORSAMI routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCFACTORSAMIRoutingResolveService;
    let service: TCFACTORSAMIService;
    let resultTCFACTORSAMI: ITCFACTORSAMI | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCFACTORSAMIRoutingResolveService);
      service = TestBed.inject(TCFACTORSAMIService);
      resultTCFACTORSAMI = undefined;
    });

    describe('resolve', () => {
      it('should return ITCFACTORSAMI returned by find', () => {
        // GIVEN
        service.find = jest.fn(idFactorSami => of(new HttpResponse({ body: { idFactorSami } })));
        mockActivatedRouteSnapshot.params = { idFactorSami: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCFACTORSAMI = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCFACTORSAMI).toEqual({ idFactorSami: 123 });
      });

      it('should return new ITCFACTORSAMI if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCFACTORSAMI = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCFACTORSAMI).toEqual(new TCFACTORSAMI());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idFactorSami: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCFACTORSAMI = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCFACTORSAMI).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
