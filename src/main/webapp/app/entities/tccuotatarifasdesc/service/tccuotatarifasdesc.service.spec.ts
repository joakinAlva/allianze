import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCCUOTATARIFASDESC, TCCUOTATARIFASDESC } from '../tccuotatarifasdesc.model';

import { TCCUOTATARIFASDESCService } from './tccuotatarifasdesc.service';

describe('Service Tests', () => {
  describe('TCCUOTATARIFASDESC Service', () => {
    let service: TCCUOTATARIFASDESCService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCCUOTATARIFASDESC;
    let expectedResult: ITCCUOTATARIFASDESC | ITCCUOTATARIFASDESC[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCCUOTATARIFASDESCService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idCuotaTarifaSdesc: 0,
        edad: 'AAAAAAA',
        valorBa: 0,
        valorBaCovid: 0,
        valorBipTres: 0,
        valorBipSeis: 0,
        valorBit: 0,
        valorMa: 0,
        valorDi: 0,
        valorTi: 0,
        valorGff: 0,
        valorGffCovid: 0,
        valorCa: 0,
        valorGe: 0,
        valorIqUno: 0,
        valorIqDos: 0,
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

      it('should create a TCCUOTATARIFASDESC', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCCUOTATARIFASDESC()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCCUOTATARIFASDESC', () => {
        const returnedFromService = Object.assign(
          {
            idCuotaTarifaSdesc: 1,
            edad: 'BBBBBB',
            valorBa: 1,
            valorBaCovid: 1,
            valorBipTres: 1,
            valorBipSeis: 1,
            valorBit: 1,
            valorMa: 1,
            valorDi: 1,
            valorTi: 1,
            valorGff: 1,
            valorGffCovid: 1,
            valorCa: 1,
            valorGe: 1,
            valorIqUno: 1,
            valorIqDos: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCCUOTATARIFASDESC', () => {
        const patchObject = Object.assign(
          {
            valorBa: 1,
            valorBaCovid: 1,
            valorBipSeis: 1,
            valorMa: 1,
            valorDi: 1,
            valorTi: 1,
            valorGff: 1,
            valorCa: 1,
            valorIqUno: 1,
          },
          new TCCUOTATARIFASDESC()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCCUOTATARIFASDESC', () => {
        const returnedFromService = Object.assign(
          {
            idCuotaTarifaSdesc: 1,
            edad: 'BBBBBB',
            valorBa: 1,
            valorBaCovid: 1,
            valorBipTres: 1,
            valorBipSeis: 1,
            valorBit: 1,
            valorMa: 1,
            valorDi: 1,
            valorTi: 1,
            valorGff: 1,
            valorGffCovid: 1,
            valorCa: 1,
            valorGe: 1,
            valorIqUno: 1,
            valorIqDos: 1,
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

      it('should delete a TCCUOTATARIFASDESC', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCCUOTATARIFASDESCToCollectionIfMissing', () => {
        it('should add a TCCUOTATARIFASDESC to an empty array', () => {
          const tCCUOTATARIFASDESC: ITCCUOTATARIFASDESC = { idCuotaTarifaSdesc: 123 };
          expectedResult = service.addTCCUOTATARIFASDESCToCollectionIfMissing([], tCCUOTATARIFASDESC);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCUOTATARIFASDESC);
        });

        it('should not add a TCCUOTATARIFASDESC to an array that contains it', () => {
          const tCCUOTATARIFASDESC: ITCCUOTATARIFASDESC = { idCuotaTarifaSdesc: 123 };
          const tCCUOTATARIFASDESCCollection: ITCCUOTATARIFASDESC[] = [
            {
              ...tCCUOTATARIFASDESC,
            },
            { idCuotaTarifaSdesc: 456 },
          ];
          expectedResult = service.addTCCUOTATARIFASDESCToCollectionIfMissing(tCCUOTATARIFASDESCCollection, tCCUOTATARIFASDESC);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCCUOTATARIFASDESC to an array that doesn't contain it", () => {
          const tCCUOTATARIFASDESC: ITCCUOTATARIFASDESC = { idCuotaTarifaSdesc: 123 };
          const tCCUOTATARIFASDESCCollection: ITCCUOTATARIFASDESC[] = [{ idCuotaTarifaSdesc: 456 }];
          expectedResult = service.addTCCUOTATARIFASDESCToCollectionIfMissing(tCCUOTATARIFASDESCCollection, tCCUOTATARIFASDESC);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCUOTATARIFASDESC);
        });

        it('should add only unique TCCUOTATARIFASDESC to an array', () => {
          const tCCUOTATARIFASDESCArray: ITCCUOTATARIFASDESC[] = [
            { idCuotaTarifaSdesc: 123 },
            { idCuotaTarifaSdesc: 456 },
            { idCuotaTarifaSdesc: 96355 },
          ];
          const tCCUOTATARIFASDESCCollection: ITCCUOTATARIFASDESC[] = [{ idCuotaTarifaSdesc: 123 }];
          expectedResult = service.addTCCUOTATARIFASDESCToCollectionIfMissing(tCCUOTATARIFASDESCCollection, ...tCCUOTATARIFASDESCArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCCUOTATARIFASDESC: ITCCUOTATARIFASDESC = { idCuotaTarifaSdesc: 123 };
          const tCCUOTATARIFASDESC2: ITCCUOTATARIFASDESC = { idCuotaTarifaSdesc: 456 };
          expectedResult = service.addTCCUOTATARIFASDESCToCollectionIfMissing([], tCCUOTATARIFASDESC, tCCUOTATARIFASDESC2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCUOTATARIFASDESC);
          expect(expectedResult).toContain(tCCUOTATARIFASDESC2);
        });

        it('should accept null and undefined values', () => {
          const tCCUOTATARIFASDESC: ITCCUOTATARIFASDESC = { idCuotaTarifaSdesc: 123 };
          expectedResult = service.addTCCUOTATARIFASDESCToCollectionIfMissing([], null, tCCUOTATARIFASDESC, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCUOTATARIFASDESC);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
