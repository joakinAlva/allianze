jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCCUOTAPROPUESTAService } from '../service/tccuotapropuesta.service';
import { ITCCUOTAPROPUESTA, TCCUOTAPROPUESTA } from '../tccuotapropuesta.model';

import { TCCUOTAPROPUESTAUpdateComponent } from './tccuotapropuesta-update.component';

describe('Component Tests', () => {
  describe('TCCUOTAPROPUESTA Management Update Component', () => {
    let comp: TCCUOTAPROPUESTAUpdateComponent;
    let fixture: ComponentFixture<TCCUOTAPROPUESTAUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCCUOTAPROPUESTAService: TCCUOTAPROPUESTAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCUOTAPROPUESTAUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCCUOTAPROPUESTAUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCUOTAPROPUESTAUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCCUOTAPROPUESTAService = TestBed.inject(TCCUOTAPROPUESTAService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCCUOTAPROPUESTA: ITCCUOTAPROPUESTA = { idCuotaPropuesta: 456 };

        activatedRoute.data = of({ tCCUOTAPROPUESTA });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCCUOTAPROPUESTA));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCUOTAPROPUESTA = { idCuotaPropuesta: 123 };
        spyOn(tCCUOTAPROPUESTAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCUOTAPROPUESTA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCUOTAPROPUESTA }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCCUOTAPROPUESTAService.update).toHaveBeenCalledWith(tCCUOTAPROPUESTA);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCUOTAPROPUESTA = new TCCUOTAPROPUESTA();
        spyOn(tCCUOTAPROPUESTAService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCUOTAPROPUESTA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCUOTAPROPUESTA }));
        saveSubject.complete();

        // THEN
        expect(tCCUOTAPROPUESTAService.create).toHaveBeenCalledWith(tCCUOTAPROPUESTA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCUOTAPROPUESTA = { idCuotaPropuesta: 123 };
        spyOn(tCCUOTAPROPUESTAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCUOTAPROPUESTA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCCUOTAPROPUESTAService.update).toHaveBeenCalledWith(tCCUOTAPROPUESTA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
