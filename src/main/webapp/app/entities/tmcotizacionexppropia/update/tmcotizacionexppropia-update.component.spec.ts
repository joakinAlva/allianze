jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TMCOTIZACIONEXPPROPIAService } from '../service/tmcotizacionexppropia.service';
import { ITMCOTIZACIONEXPPROPIA, TMCOTIZACIONEXPPROPIA } from '../tmcotizacionexppropia.model';

import { TMCOTIZACIONEXPPROPIAUpdateComponent } from './tmcotizacionexppropia-update.component';

describe('Component Tests', () => {
  describe('TMCOTIZACIONEXPPROPIA Management Update Component', () => {
    let comp: TMCOTIZACIONEXPPROPIAUpdateComponent;
    let fixture: ComponentFixture<TMCOTIZACIONEXPPROPIAUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tMCOTIZACIONEXPPROPIAService: TMCOTIZACIONEXPPROPIAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TMCOTIZACIONEXPPROPIAUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TMCOTIZACIONEXPPROPIAUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TMCOTIZACIONEXPPROPIAUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tMCOTIZACIONEXPPROPIAService = TestBed.inject(TMCOTIZACIONEXPPROPIAService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA = { idCotizacionExpPropia: 456 };

        activatedRoute.data = of({ tMCOTIZACIONEXPPROPIA });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tMCOTIZACIONEXPPROPIA));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMCOTIZACIONEXPPROPIA = { idCotizacionExpPropia: 123 };
        spyOn(tMCOTIZACIONEXPPROPIAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMCOTIZACIONEXPPROPIA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tMCOTIZACIONEXPPROPIA }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tMCOTIZACIONEXPPROPIAService.update).toHaveBeenCalledWith(tMCOTIZACIONEXPPROPIA);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMCOTIZACIONEXPPROPIA = new TMCOTIZACIONEXPPROPIA();
        spyOn(tMCOTIZACIONEXPPROPIAService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMCOTIZACIONEXPPROPIA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tMCOTIZACIONEXPPROPIA }));
        saveSubject.complete();

        // THEN
        expect(tMCOTIZACIONEXPPROPIAService.create).toHaveBeenCalledWith(tMCOTIZACIONEXPPROPIA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMCOTIZACIONEXPPROPIA = { idCotizacionExpPropia: 123 };
        spyOn(tMCOTIZACIONEXPPROPIAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMCOTIZACIONEXPPROPIA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tMCOTIZACIONEXPPROPIAService.update).toHaveBeenCalledWith(tMCOTIZACIONEXPPROPIA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
