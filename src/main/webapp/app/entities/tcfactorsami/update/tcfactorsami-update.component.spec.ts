jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCFACTORSAMIService } from '../service/tcfactorsami.service';
import { ITCFACTORSAMI, TCFACTORSAMI } from '../tcfactorsami.model';

import { TCFACTORSAMIUpdateComponent } from './tcfactorsami-update.component';

describe('Component Tests', () => {
  describe('TCFACTORSAMI Management Update Component', () => {
    let comp: TCFACTORSAMIUpdateComponent;
    let fixture: ComponentFixture<TCFACTORSAMIUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCFACTORSAMIService: TCFACTORSAMIService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCFACTORSAMIUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCFACTORSAMIUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCFACTORSAMIUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCFACTORSAMIService = TestBed.inject(TCFACTORSAMIService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCFACTORSAMI: ITCFACTORSAMI = { idFactorSami: 456 };

        activatedRoute.data = of({ tCFACTORSAMI });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCFACTORSAMI));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCFACTORSAMI = { idFactorSami: 123 };
        spyOn(tCFACTORSAMIService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCFACTORSAMI });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCFACTORSAMI }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCFACTORSAMIService.update).toHaveBeenCalledWith(tCFACTORSAMI);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCFACTORSAMI = new TCFACTORSAMI();
        spyOn(tCFACTORSAMIService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCFACTORSAMI });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCFACTORSAMI }));
        saveSubject.complete();

        // THEN
        expect(tCFACTORSAMIService.create).toHaveBeenCalledWith(tCFACTORSAMI);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCFACTORSAMI = { idFactorSami: 123 };
        spyOn(tCFACTORSAMIService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCFACTORSAMI });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCFACTORSAMIService.update).toHaveBeenCalledWith(tCFACTORSAMI);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
