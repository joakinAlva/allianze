jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TMASEGURADOService } from '../service/tmasegurado.service';
import { ITMASEGURADO, TMASEGURADO } from '../tmasegurado.model';

import { TMASEGURADOUpdateComponent } from './tmasegurado-update.component';

describe('Component Tests', () => {
  describe('TMASEGURADO Management Update Component', () => {
    let comp: TMASEGURADOUpdateComponent;
    let fixture: ComponentFixture<TMASEGURADOUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tMASEGURADOService: TMASEGURADOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TMASEGURADOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TMASEGURADOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TMASEGURADOUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tMASEGURADOService = TestBed.inject(TMASEGURADOService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tMASEGURADO: ITMASEGURADO = { idAsegurados: 456 };

        activatedRoute.data = of({ tMASEGURADO });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tMASEGURADO));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMASEGURADO = { idAsegurados: 123 };
        spyOn(tMASEGURADOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMASEGURADO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tMASEGURADO }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tMASEGURADOService.update).toHaveBeenCalledWith(tMASEGURADO);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMASEGURADO = new TMASEGURADO();
        spyOn(tMASEGURADOService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMASEGURADO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tMASEGURADO }));
        saveSubject.complete();

        // THEN
        expect(tMASEGURADOService.create).toHaveBeenCalledWith(tMASEGURADO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMASEGURADO = { idAsegurados: 123 };
        spyOn(tMASEGURADOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMASEGURADO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tMASEGURADOService.update).toHaveBeenCalledWith(tMASEGURADO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
