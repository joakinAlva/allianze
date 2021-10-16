jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TMCOTIZACIONINFOService } from '../service/tmcotizacioninfo.service';
import { ITMCOTIZACIONINFO, TMCOTIZACIONINFO } from '../tmcotizacioninfo.model';

import { TMCOTIZACIONINFOUpdateComponent } from './tmcotizacioninfo-update.component';

describe('Component Tests', () => {
  describe('TMCOTIZACIONINFO Management Update Component', () => {
    let comp: TMCOTIZACIONINFOUpdateComponent;
    let fixture: ComponentFixture<TMCOTIZACIONINFOUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tMCOTIZACIONINFOService: TMCOTIZACIONINFOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TMCOTIZACIONINFOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TMCOTIZACIONINFOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TMCOTIZACIONINFOUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tMCOTIZACIONINFOService = TestBed.inject(TMCOTIZACIONINFOService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tMCOTIZACIONINFO: ITMCOTIZACIONINFO = { idCotizacionInfo: 456 };

        activatedRoute.data = of({ tMCOTIZACIONINFO });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tMCOTIZACIONINFO));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMCOTIZACIONINFO = { idCotizacionInfo: 123 };
        spyOn(tMCOTIZACIONINFOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMCOTIZACIONINFO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tMCOTIZACIONINFO }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tMCOTIZACIONINFOService.update).toHaveBeenCalledWith(tMCOTIZACIONINFO);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMCOTIZACIONINFO = new TMCOTIZACIONINFO();
        spyOn(tMCOTIZACIONINFOService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMCOTIZACIONINFO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tMCOTIZACIONINFO }));
        saveSubject.complete();

        // THEN
        expect(tMCOTIZACIONINFOService.create).toHaveBeenCalledWith(tMCOTIZACIONINFO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMCOTIZACIONINFO = { idCotizacionInfo: 123 };
        spyOn(tMCOTIZACIONINFOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMCOTIZACIONINFO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tMCOTIZACIONINFOService.update).toHaveBeenCalledWith(tMCOTIZACIONINFO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
