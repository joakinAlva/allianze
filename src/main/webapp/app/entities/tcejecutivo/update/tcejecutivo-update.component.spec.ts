jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCEJECUTIVOService } from '../service/tcejecutivo.service';
import { ITCEJECUTIVO, TCEJECUTIVO } from '../tcejecutivo.model';

import { TCEJECUTIVOUpdateComponent } from './tcejecutivo-update.component';

describe('Component Tests', () => {
  describe('TCEJECUTIVO Management Update Component', () => {
    let comp: TCEJECUTIVOUpdateComponent;
    let fixture: ComponentFixture<TCEJECUTIVOUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCEJECUTIVOService: TCEJECUTIVOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCEJECUTIVOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCEJECUTIVOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCEJECUTIVOUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCEJECUTIVOService = TestBed.inject(TCEJECUTIVOService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCEJECUTIVO: ITCEJECUTIVO = { idEjecutivo: 456 };

        activatedRoute.data = of({ tCEJECUTIVO });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCEJECUTIVO));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCEJECUTIVO = { idEjecutivo: 123 };
        spyOn(tCEJECUTIVOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCEJECUTIVO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCEJECUTIVO }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCEJECUTIVOService.update).toHaveBeenCalledWith(tCEJECUTIVO);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCEJECUTIVO = new TCEJECUTIVO();
        spyOn(tCEJECUTIVOService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCEJECUTIVO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCEJECUTIVO }));
        saveSubject.complete();

        // THEN
        expect(tCEJECUTIVOService.create).toHaveBeenCalledWith(tCEJECUTIVO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCEJECUTIVO = { idEjecutivo: 123 };
        spyOn(tCEJECUTIVOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCEJECUTIVO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCEJECUTIVOService.update).toHaveBeenCalledWith(tCEJECUTIVO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
