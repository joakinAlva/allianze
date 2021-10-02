jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCCOVIDService } from '../service/tccovid.service';
import { ITCCOVID, TCCOVID } from '../tccovid.model';

import { TCCOVIDUpdateComponent } from './tccovid-update.component';

describe('Component Tests', () => {
  describe('TCCOVID Management Update Component', () => {
    let comp: TCCOVIDUpdateComponent;
    let fixture: ComponentFixture<TCCOVIDUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCCOVIDService: TCCOVIDService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCOVIDUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCCOVIDUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCOVIDUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCCOVIDService = TestBed.inject(TCCOVIDService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCCOVID: ITCCOVID = { idCovid: 456 };

        activatedRoute.data = of({ tCCOVID });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCCOVID));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCOVID = { idCovid: 123 };
        spyOn(tCCOVIDService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCOVID });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCOVID }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCCOVIDService.update).toHaveBeenCalledWith(tCCOVID);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCOVID = new TCCOVID();
        spyOn(tCCOVIDService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCOVID });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCOVID }));
        saveSubject.complete();

        // THEN
        expect(tCCOVIDService.create).toHaveBeenCalledWith(tCCOVID);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCOVID = { idCovid: 123 };
        spyOn(tCCOVIDService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCOVID });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCCOVIDService.update).toHaveBeenCalledWith(tCCOVID);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
