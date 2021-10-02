jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCSUBGRUPOService } from '../service/tcsubgrupo.service';
import { ITCSUBGRUPO, TCSUBGRUPO } from '../tcsubgrupo.model';

import { TCSUBGRUPOUpdateComponent } from './tcsubgrupo-update.component';

describe('Component Tests', () => {
  describe('TCSUBGRUPO Management Update Component', () => {
    let comp: TCSUBGRUPOUpdateComponent;
    let fixture: ComponentFixture<TCSUBGRUPOUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCSUBGRUPOService: TCSUBGRUPOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCSUBGRUPOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCSUBGRUPOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCSUBGRUPOUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCSUBGRUPOService = TestBed.inject(TCSUBGRUPOService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCSUBGRUPO: ITCSUBGRUPO = { idSubGrupo: 456 };

        activatedRoute.data = of({ tCSUBGRUPO });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCSUBGRUPO));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCSUBGRUPO = { idSubGrupo: 123 };
        spyOn(tCSUBGRUPOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCSUBGRUPO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCSUBGRUPO }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCSUBGRUPOService.update).toHaveBeenCalledWith(tCSUBGRUPO);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCSUBGRUPO = new TCSUBGRUPO();
        spyOn(tCSUBGRUPOService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCSUBGRUPO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCSUBGRUPO }));
        saveSubject.complete();

        // THEN
        expect(tCSUBGRUPOService.create).toHaveBeenCalledWith(tCSUBGRUPO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCSUBGRUPO = { idSubGrupo: 123 };
        spyOn(tCSUBGRUPOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCSUBGRUPO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCSUBGRUPOService.update).toHaveBeenCalledWith(tCSUBGRUPO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
