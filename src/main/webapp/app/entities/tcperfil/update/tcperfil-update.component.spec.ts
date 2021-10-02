jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCPERFILService } from '../service/tcperfil.service';
import { ITCPERFIL, TCPERFIL } from '../tcperfil.model';

import { TCPERFILUpdateComponent } from './tcperfil-update.component';

describe('Component Tests', () => {
  describe('TCPERFIL Management Update Component', () => {
    let comp: TCPERFILUpdateComponent;
    let fixture: ComponentFixture<TCPERFILUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCPERFILService: TCPERFILService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCPERFILUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCPERFILUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCPERFILUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCPERFILService = TestBed.inject(TCPERFILService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCPERFIL: ITCPERFIL = { idPerfil: 456 };

        activatedRoute.data = of({ tCPERFIL });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCPERFIL));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCPERFIL = { idPerfil: 123 };
        spyOn(tCPERFILService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCPERFIL });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCPERFIL }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCPERFILService.update).toHaveBeenCalledWith(tCPERFIL);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCPERFIL = new TCPERFIL();
        spyOn(tCPERFILService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCPERFIL });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCPERFIL }));
        saveSubject.complete();

        // THEN
        expect(tCPERFILService.create).toHaveBeenCalledWith(tCPERFIL);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCPERFIL = { idPerfil: 123 };
        spyOn(tCPERFILService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCPERFIL });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCPERFILService.update).toHaveBeenCalledWith(tCPERFIL);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
