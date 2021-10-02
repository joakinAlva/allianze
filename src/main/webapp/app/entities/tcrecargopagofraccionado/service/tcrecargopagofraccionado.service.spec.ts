import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCRECARGOPAGOFRACCIONADO, TCRECARGOPAGOFRACCIONADO } from '../tcrecargopagofraccionado.model';

import { TCRECARGOPAGOFRACCIONADOService } from './tcrecargopagofraccionado.service';

describe('Service Tests', () => {
  describe('TCRECARGOPAGOFRACCIONADO Service', () => {
    let service: TCRECARGOPAGOFRACCIONADOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCRECARGOPAGOFRACCIONADO;
    let expectedResult: ITCRECARGOPAGOFRACCIONADO | ITCRECARGOPAGOFRACCIONADO[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCRECARGOPAGOFRACCIONADOService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idRecargoPagoFraccionado: 0,
        periodo: 'AAAAAAA',
        porcentaje: 0,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TCRECARGOPAGOFRACCIONADO', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCRECARGOPAGOFRACCIONADO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCRECARGOPAGOFRACCIONADO', () => {
        const returnedFromService = Object.assign(
          {
            idRecargoPagoFraccionado: 1,
            periodo: 'BBBBBB',
            porcentaje: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCRECARGOPAGOFRACCIONADO', () => {
        const patchObject = Object.assign({}, new TCRECARGOPAGOFRACCIONADO());

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCRECARGOPAGOFRACCIONADO', () => {
        const returnedFromService = Object.assign(
          {
            idRecargoPagoFraccionado: 1,
            periodo: 'BBBBBB',
            porcentaje: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TCRECARGOPAGOFRACCIONADO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCRECARGOPAGOFRACCIONADOToCollectionIfMissing', () => {
        it('should add a TCRECARGOPAGOFRACCIONADO to an empty array', () => {
          const tCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO = { idRecargoPagoFraccionado: 123 };
          expectedResult = service.addTCRECARGOPAGOFRACCIONADOToCollectionIfMissing([], tCRECARGOPAGOFRACCIONADO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCRECARGOPAGOFRACCIONADO);
        });

        it('should not add a TCRECARGOPAGOFRACCIONADO to an array that contains it', () => {
          const tCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO = { idRecargoPagoFraccionado: 123 };
          const tCRECARGOPAGOFRACCIONADOCollection: ITCRECARGOPAGOFRACCIONADO[] = [
            {
              ...tCRECARGOPAGOFRACCIONADO,
            },
            { idRecargoPagoFraccionado: 456 },
          ];
          expectedResult = service.addTCRECARGOPAGOFRACCIONADOToCollectionIfMissing(
            tCRECARGOPAGOFRACCIONADOCollection,
            tCRECARGOPAGOFRACCIONADO
          );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCRECARGOPAGOFRACCIONADO to an array that doesn't contain it", () => {
          const tCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO = { idRecargoPagoFraccionado: 123 };
          const tCRECARGOPAGOFRACCIONADOCollection: ITCRECARGOPAGOFRACCIONADO[] = [{ idRecargoPagoFraccionado: 456 }];
          expectedResult = service.addTCRECARGOPAGOFRACCIONADOToCollectionIfMissing(
            tCRECARGOPAGOFRACCIONADOCollection,
            tCRECARGOPAGOFRACCIONADO
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCRECARGOPAGOFRACCIONADO);
        });

        it('should add only unique TCRECARGOPAGOFRACCIONADO to an array', () => {
          const tCRECARGOPAGOFRACCIONADOArray: ITCRECARGOPAGOFRACCIONADO[] = [
            { idRecargoPagoFraccionado: 123 },
            { idRecargoPagoFraccionado: 456 },
            { idRecargoPagoFraccionado: 8174 },
          ];
          const tCRECARGOPAGOFRACCIONADOCollection: ITCRECARGOPAGOFRACCIONADO[] = [{ idRecargoPagoFraccionado: 123 }];
          expectedResult = service.addTCRECARGOPAGOFRACCIONADOToCollectionIfMissing(
            tCRECARGOPAGOFRACCIONADOCollection,
            ...tCRECARGOPAGOFRACCIONADOArray
          );
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO = { idRecargoPagoFraccionado: 123 };
          const tCRECARGOPAGOFRACCIONADO2: ITCRECARGOPAGOFRACCIONADO = { idRecargoPagoFraccionado: 456 };
          expectedResult = service.addTCRECARGOPAGOFRACCIONADOToCollectionIfMissing(
            [],
            tCRECARGOPAGOFRACCIONADO,
            tCRECARGOPAGOFRACCIONADO2
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCRECARGOPAGOFRACCIONADO);
          expect(expectedResult).toContain(tCRECARGOPAGOFRACCIONADO2);
        });

        it('should accept null and undefined values', () => {
          const tCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO = { idRecargoPagoFraccionado: 123 };
          expectedResult = service.addTCRECARGOPAGOFRACCIONADOToCollectionIfMissing([], null, tCRECARGOPAGOFRACCIONADO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCRECARGOPAGOFRACCIONADO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
