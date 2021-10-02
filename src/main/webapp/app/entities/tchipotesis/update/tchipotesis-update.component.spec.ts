jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCHIPOTESISService } from '../service/tchipotesis.service';
import { ITCHIPOTESIS, TCHIPOTESIS } from '../tchipotesis.model';

import { TCHIPOTESISUpdateComponent } from './tchipotesis-update.component';

describe('Component Tests', () => {
  describe('TCHIPOTESIS Management Update Component', () => {
    let comp: TCHIPOTESISUpdateComponent;
    let fixture: ComponentFixture<TCHIPOTESISUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCHIPOTESISService: TCHIPOTESISService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCHIPOTESISUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCHIPOTESISUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCHIPOTESISUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCHIPOTESISService = TestBed.inject(TCHIPOTESISService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCHIPOTESIS: ITCHIPOTESIS = { idHipotesis: 456 };

        activatedRoute.data = of({ tCHIPOTESIS });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCHIPOTESIS));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCHIPOTESIS = { idHipotesis: 123 };
        spyOn(tCHIPOTESISService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCHIPOTESIS });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCHIPOTESIS }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCHIPOTESISService.update).toHaveBeenCalledWith(tCHIPOTESIS);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCHIPOTESIS = new TCHIPOTESIS();
        spyOn(tCHIPOTESISService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCHIPOTESIS });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCHIPOTESIS }));
        saveSubject.complete();

        // THEN
        expect(tCHIPOTESISService.create).toHaveBeenCalledWith(tCHIPOTESIS);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCHIPOTESIS = { idHipotesis: 123 };
        spyOn(tCHIPOTESISService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCHIPOTESIS });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCHIPOTESISService.update).toHaveBeenCalledWith(tCHIPOTESIS);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
