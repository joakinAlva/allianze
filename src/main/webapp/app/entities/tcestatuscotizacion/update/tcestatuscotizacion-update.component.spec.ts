jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCESTATUSCOTIZACIONService } from '../service/tcestatuscotizacion.service';
import { ITCESTATUSCOTIZACION, TCESTATUSCOTIZACION } from '../tcestatuscotizacion.model';

import { TCESTATUSCOTIZACIONUpdateComponent } from './tcestatuscotizacion-update.component';

describe('Component Tests', () => {
  describe('TCESTATUSCOTIZACION Management Update Component', () => {
    let comp: TCESTATUSCOTIZACIONUpdateComponent;
    let fixture: ComponentFixture<TCESTATUSCOTIZACIONUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCESTATUSCOTIZACIONService: TCESTATUSCOTIZACIONService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCESTATUSCOTIZACIONUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCESTATUSCOTIZACIONUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCESTATUSCOTIZACIONUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCESTATUSCOTIZACIONService = TestBed.inject(TCESTATUSCOTIZACIONService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCESTATUSCOTIZACION: ITCESTATUSCOTIZACION = { idEstatusCotizacion: 456 };

        activatedRoute.data = of({ tCESTATUSCOTIZACION });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCESTATUSCOTIZACION));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCESTATUSCOTIZACION = { idEstatusCotizacion: 123 };
        spyOn(tCESTATUSCOTIZACIONService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCESTATUSCOTIZACION });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCESTATUSCOTIZACION }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCESTATUSCOTIZACIONService.update).toHaveBeenCalledWith(tCESTATUSCOTIZACION);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCESTATUSCOTIZACION = new TCESTATUSCOTIZACION();
        spyOn(tCESTATUSCOTIZACIONService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCESTATUSCOTIZACION });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCESTATUSCOTIZACION }));
        saveSubject.complete();

        // THEN
        expect(tCESTATUSCOTIZACIONService.create).toHaveBeenCalledWith(tCESTATUSCOTIZACION);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCESTATUSCOTIZACION = { idEstatusCotizacion: 123 };
        spyOn(tCESTATUSCOTIZACIONService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCESTATUSCOTIZACION });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCESTATUSCOTIZACIONService.update).toHaveBeenCalledWith(tCESTATUSCOTIZACION);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
