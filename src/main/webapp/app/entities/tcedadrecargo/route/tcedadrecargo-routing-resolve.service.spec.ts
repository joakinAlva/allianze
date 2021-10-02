jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCEDADRECARGO, TCEDADRECARGO } from '../tcedadrecargo.model';
import { TCEDADRECARGOService } from '../service/tcedadrecargo.service';

import { TCEDADRECARGORoutingResolveService } from './tcedadrecargo-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCEDADRECARGO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCEDADRECARGORoutingResolveService;
    let service: TCEDADRECARGOService;
    let resultTCEDADRECARGO: ITCEDADRECARGO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCEDADRECARGORoutingResolveService);
      service = TestBed.inject(TCEDADRECARGOService);
      resultTCEDADRECARGO = undefined;
    });

    describe('resolve', () => {
      it('should return ITCEDADRECARGO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idEdadRecargo => of(new HttpResponse({ body: { idEdadRecargo } })));
        mockActivatedRouteSnapshot.params = { idEdadRecargo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCEDADRECARGO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCEDADRECARGO).toEqual({ idEdadRecargo: 123 });
      });

      it('should return new ITCEDADRECARGO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCEDADRECARGO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCEDADRECARGO).toEqual(new TCEDADRECARGO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idEdadRecargo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCEDADRECARGO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCEDADRECARGO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
