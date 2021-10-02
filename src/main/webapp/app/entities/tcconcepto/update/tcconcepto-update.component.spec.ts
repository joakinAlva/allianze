jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCCONCEPTOService } from '../service/tcconcepto.service';
import { ITCCONCEPTO, TCCONCEPTO } from '../tcconcepto.model';

import { TCCONCEPTOUpdateComponent } from './tcconcepto-update.component';

describe('Component Tests', () => {
  describe('TCCONCEPTO Management Update Component', () => {
    let comp: TCCONCEPTOUpdateComponent;
    let fixture: ComponentFixture<TCCONCEPTOUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCCONCEPTOService: TCCONCEPTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCONCEPTOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCCONCEPTOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCONCEPTOUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCCONCEPTOService = TestBed.inject(TCCONCEPTOService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCCONCEPTO: ITCCONCEPTO = { idConcepto: 456 };

        activatedRoute.data = of({ tCCONCEPTO });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCCONCEPTO));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCONCEPTO = { idConcepto: 123 };
        spyOn(tCCONCEPTOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCONCEPTO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCONCEPTO }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCCONCEPTOService.update).toHaveBeenCalledWith(tCCONCEPTO);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCONCEPTO = new TCCONCEPTO();
        spyOn(tCCONCEPTOService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCONCEPTO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCONCEPTO }));
        saveSubject.complete();

        // THEN
        expect(tCCONCEPTOService.create).toHaveBeenCalledWith(tCCONCEPTO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCONCEPTO = { idConcepto: 123 };
        spyOn(tCCONCEPTOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCONCEPTO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCCONCEPTOService.update).toHaveBeenCalledWith(tCCONCEPTO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
