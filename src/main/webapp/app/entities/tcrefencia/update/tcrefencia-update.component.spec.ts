jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCREFENCIAService } from '../service/tcrefencia.service';
import { ITCREFENCIA, TCREFENCIA } from '../tcrefencia.model';

import { TCREFENCIAUpdateComponent } from './tcrefencia-update.component';

describe('Component Tests', () => {
  describe('TCREFENCIA Management Update Component', () => {
    let comp: TCREFENCIAUpdateComponent;
    let fixture: ComponentFixture<TCREFENCIAUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCREFENCIAService: TCREFENCIAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCREFENCIAUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCREFENCIAUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCREFENCIAUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCREFENCIAService = TestBed.inject(TCREFENCIAService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCREFENCIA: ITCREFENCIA = { idReferencia: 456 };

        activatedRoute.data = of({ tCREFENCIA });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCREFENCIA));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCREFENCIA = { idReferencia: 123 };
        spyOn(tCREFENCIAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCREFENCIA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCREFENCIA }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCREFENCIAService.update).toHaveBeenCalledWith(tCREFENCIA);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCREFENCIA = new TCREFENCIA();
        spyOn(tCREFENCIAService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCREFENCIA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCREFENCIA }));
        saveSubject.complete();

        // THEN
        expect(tCREFENCIAService.create).toHaveBeenCalledWith(tCREFENCIA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCREFENCIA = { idReferencia: 123 };
        spyOn(tCREFENCIAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCREFENCIA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCREFENCIAService.update).toHaveBeenCalledWith(tCREFENCIA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
