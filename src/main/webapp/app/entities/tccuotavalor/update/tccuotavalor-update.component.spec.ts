jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCCUOTAVALORService } from '../service/tccuotavalor.service';
import { ITCCUOTAVALOR, TCCUOTAVALOR } from '../tccuotavalor.model';

import { TCCUOTAVALORUpdateComponent } from './tccuotavalor-update.component';

describe('Component Tests', () => {
  describe('TCCUOTAVALOR Management Update Component', () => {
    let comp: TCCUOTAVALORUpdateComponent;
    let fixture: ComponentFixture<TCCUOTAVALORUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCCUOTAVALORService: TCCUOTAVALORService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCUOTAVALORUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCCUOTAVALORUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCUOTAVALORUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCCUOTAVALORService = TestBed.inject(TCCUOTAVALORService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCCUOTAVALOR: ITCCUOTAVALOR = { idCuotaValor: 456 };

        activatedRoute.data = of({ tCCUOTAVALOR });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCCUOTAVALOR));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCUOTAVALOR = { idCuotaValor: 123 };
        spyOn(tCCUOTAVALORService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCUOTAVALOR });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCUOTAVALOR }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCCUOTAVALORService.update).toHaveBeenCalledWith(tCCUOTAVALOR);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCUOTAVALOR = new TCCUOTAVALOR();
        spyOn(tCCUOTAVALORService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCUOTAVALOR });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCUOTAVALOR }));
        saveSubject.complete();

        // THEN
        expect(tCCUOTAVALORService.create).toHaveBeenCalledWith(tCCUOTAVALOR);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCUOTAVALOR = { idCuotaValor: 123 };
        spyOn(tCCUOTAVALORService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCUOTAVALOR });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCCUOTAVALORService.update).toHaveBeenCalledWith(tCCUOTAVALOR);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
