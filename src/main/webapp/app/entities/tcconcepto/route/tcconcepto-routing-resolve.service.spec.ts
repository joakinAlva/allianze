jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCCONCEPTO, TCCONCEPTO } from '../tcconcepto.model';
import { TCCONCEPTOService } from '../service/tcconcepto.service';

import { TCCONCEPTORoutingResolveService } from './tcconcepto-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCCONCEPTO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCCONCEPTORoutingResolveService;
    let service: TCCONCEPTOService;
    let resultTCCONCEPTO: ITCCONCEPTO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCCONCEPTORoutingResolveService);
      service = TestBed.inject(TCCONCEPTOService);
      resultTCCONCEPTO = undefined;
    });

    describe('resolve', () => {
      it('should return ITCCONCEPTO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idConcepto => of(new HttpResponse({ body: { idConcepto } })));
        mockActivatedRouteSnapshot.params = { idConcepto: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCONCEPTO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCONCEPTO).toEqual({ idConcepto: 123 });
      });

      it('should return new ITCCONCEPTO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCONCEPTO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCCONCEPTO).toEqual(new TCCONCEPTO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idConcepto: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCONCEPTO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCONCEPTO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
