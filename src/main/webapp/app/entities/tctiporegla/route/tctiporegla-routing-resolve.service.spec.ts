jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCTIPOREGLA, TCTIPOREGLA } from '../tctiporegla.model';
import { TCTIPOREGLAService } from '../service/tctiporegla.service';

import { TCTIPOREGLARoutingResolveService } from './tctiporegla-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCTIPOREGLA routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCTIPOREGLARoutingResolveService;
    let service: TCTIPOREGLAService;
    let resultTCTIPOREGLA: ITCTIPOREGLA | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCTIPOREGLARoutingResolveService);
      service = TestBed.inject(TCTIPOREGLAService);
      resultTCTIPOREGLA = undefined;
    });

    describe('resolve', () => {
      it('should return ITCTIPOREGLA returned by find', () => {
        // GIVEN
        service.find = jest.fn(idTipoRegla => of(new HttpResponse({ body: { idTipoRegla } })));
        mockActivatedRouteSnapshot.params = { idTipoRegla: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCTIPOREGLA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCTIPOREGLA).toEqual({ idTipoRegla: 123 });
      });

      it('should return new ITCTIPOREGLA if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCTIPOREGLA = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCTIPOREGLA).toEqual(new TCTIPOREGLA());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idTipoRegla: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCTIPOREGLA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCTIPOREGLA).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
