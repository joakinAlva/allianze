jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCTIPOREGLAService } from '../service/tctiporegla.service';
import { ITCTIPOREGLA, TCTIPOREGLA } from '../tctiporegla.model';

import { TCTIPOREGLAUpdateComponent } from './tctiporegla-update.component';

describe('Component Tests', () => {
  describe('TCTIPOREGLA Management Update Component', () => {
    let comp: TCTIPOREGLAUpdateComponent;
    let fixture: ComponentFixture<TCTIPOREGLAUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCTIPOREGLAService: TCTIPOREGLAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCTIPOREGLAUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCTIPOREGLAUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCTIPOREGLAUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCTIPOREGLAService = TestBed.inject(TCTIPOREGLAService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCTIPOREGLA: ITCTIPOREGLA = { idTipoRegla: 456 };

        activatedRoute.data = of({ tCTIPOREGLA });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCTIPOREGLA));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCTIPOREGLA = { idTipoRegla: 123 };
        spyOn(tCTIPOREGLAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCTIPOREGLA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCTIPOREGLA }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCTIPOREGLAService.update).toHaveBeenCalledWith(tCTIPOREGLA);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCTIPOREGLA = new TCTIPOREGLA();
        spyOn(tCTIPOREGLAService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCTIPOREGLA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCTIPOREGLA }));
        saveSubject.complete();

        // THEN
        expect(tCTIPOREGLAService.create).toHaveBeenCalledWith(tCTIPOREGLA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCTIPOREGLA = { idTipoRegla: 123 };
        spyOn(tCTIPOREGLAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCTIPOREGLA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCTIPOREGLAService.update).toHaveBeenCalledWith(tCTIPOREGLA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
