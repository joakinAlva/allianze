jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCSUMAASEGURADAService } from '../service/tcsumaasegurada.service';
import { ITCSUMAASEGURADA, TCSUMAASEGURADA } from '../tcsumaasegurada.model';

import { TCSUMAASEGURADAUpdateComponent } from './tcsumaasegurada-update.component';

describe('Component Tests', () => {
  describe('TCSUMAASEGURADA Management Update Component', () => {
    let comp: TCSUMAASEGURADAUpdateComponent;
    let fixture: ComponentFixture<TCSUMAASEGURADAUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCSUMAASEGURADAService: TCSUMAASEGURADAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCSUMAASEGURADAUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCSUMAASEGURADAUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCSUMAASEGURADAUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCSUMAASEGURADAService = TestBed.inject(TCSUMAASEGURADAService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCSUMAASEGURADA: ITCSUMAASEGURADA = { idSumaAsegurada: 456 };

        activatedRoute.data = of({ tCSUMAASEGURADA });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCSUMAASEGURADA));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCSUMAASEGURADA = { idSumaAsegurada: 123 };
        spyOn(tCSUMAASEGURADAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCSUMAASEGURADA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCSUMAASEGURADA }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCSUMAASEGURADAService.update).toHaveBeenCalledWith(tCSUMAASEGURADA);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCSUMAASEGURADA = new TCSUMAASEGURADA();
        spyOn(tCSUMAASEGURADAService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCSUMAASEGURADA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCSUMAASEGURADA }));
        saveSubject.complete();

        // THEN
        expect(tCSUMAASEGURADAService.create).toHaveBeenCalledWith(tCSUMAASEGURADA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCSUMAASEGURADA = { idSumaAsegurada: 123 };
        spyOn(tCSUMAASEGURADAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCSUMAASEGURADA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCSUMAASEGURADAService.update).toHaveBeenCalledWith(tCSUMAASEGURADA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
