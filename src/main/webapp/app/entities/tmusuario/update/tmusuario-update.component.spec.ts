jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TMUSUARIOService } from '../service/tmusuario.service';
import { ITMUSUARIO, TMUSUARIO } from '../tmusuario.model';
import { ITCPERFIL } from 'app/entities/tcperfil/tcperfil.model';
import { TCPERFILService } from 'app/entities/tcperfil/service/tcperfil.service';

import { TMUSUARIOUpdateComponent } from './tmusuario-update.component';

describe('Component Tests', () => {
  describe('TMUSUARIO Management Update Component', () => {
    let comp: TMUSUARIOUpdateComponent;
    let fixture: ComponentFixture<TMUSUARIOUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tMUSUARIOService: TMUSUARIOService;
    let tCPERFILService: TCPERFILService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TMUSUARIOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TMUSUARIOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TMUSUARIOUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tMUSUARIOService = TestBed.inject(TMUSUARIOService);
      tCPERFILService = TestBed.inject(TCPERFILService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call TCPERFIL query and add missing value', () => {
        const tMUSUARIO: ITMUSUARIO = { idUsuario: 456 };
        const employee: ITCPERFIL = { idPerfil: 46462 };
        tMUSUARIO.employee = employee;

        const tCPERFILCollection: ITCPERFIL[] = [{ idPerfil: 72682 }];
        spyOn(tCPERFILService, 'query').and.returnValue(of(new HttpResponse({ body: tCPERFILCollection })));
        const additionalTCPERFILS = [employee];
        const expectedCollection: ITCPERFIL[] = [...additionalTCPERFILS, ...tCPERFILCollection];
        spyOn(tCPERFILService, 'addTCPERFILToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ tMUSUARIO });
        comp.ngOnInit();

        expect(tCPERFILService.query).toHaveBeenCalled();
        expect(tCPERFILService.addTCPERFILToCollectionIfMissing).toHaveBeenCalledWith(tCPERFILCollection, ...additionalTCPERFILS);
        expect(comp.tCPERFILSSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const tMUSUARIO: ITMUSUARIO = { idUsuario: 456 };
        const employee: ITCPERFIL = { idPerfil: 2624 };
        tMUSUARIO.employee = employee;

        activatedRoute.data = of({ tMUSUARIO });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tMUSUARIO));
        expect(comp.tCPERFILSSharedCollection).toContain(employee);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMUSUARIO = { idUsuario: 123 };
        spyOn(tMUSUARIOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMUSUARIO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tMUSUARIO }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tMUSUARIOService.update).toHaveBeenCalledWith(tMUSUARIO);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMUSUARIO = new TMUSUARIO();
        spyOn(tMUSUARIOService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMUSUARIO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tMUSUARIO }));
        saveSubject.complete();

        // THEN
        expect(tMUSUARIOService.create).toHaveBeenCalledWith(tMUSUARIO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMUSUARIO = { idUsuario: 123 };
        spyOn(tMUSUARIOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMUSUARIO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tMUSUARIOService.update).toHaveBeenCalledWith(tMUSUARIO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackTCPERFILByIdPerfil', () => {
        it('Should return tracked TCPERFIL primary key', () => {
          const entity = { idPerfil: 123 };
          const trackResult = comp.trackTCPERFILByIdPerfil(0, entity);
          expect(trackResult).toEqual(entity.idPerfil);
        });
      });
    });
  });
});
