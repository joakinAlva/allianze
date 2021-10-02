import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCCUOTARIESGO, TCCUOTARIESGO } from '../tccuotariesgo.model';

import { TCCUOTARIESGOService } from './tccuotariesgo.service';

describe('Service Tests', () => {
  describe('TCCUOTARIESGO Service', () => {
    let service: TCCUOTARIESGOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCCUOTARIESGO;
    let expectedResult: ITCCUOTARIESGO | ITCCUOTARIESGO[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCCUOTARIESGOService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idCuotaRiesgo: 0,
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

      it('should create a TCCUOTARIESGO', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCCUOTARIESGO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCCUOTARIESGO', () => {
        const returnedFromService = Object.assign(
          {
            idCuotaRiesgo: 1,
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

      it('should partial update a TCCUOTARIESGO', () => {
        const patchObject = Object.assign(
          {
            valorBa: 1,
            valorBaCovid: 1,
            valorBit: 1,
            valorDi: 1,
            valorGe: 1,
            valorIqUno: 1,
            valorIqDos: 1,
          },
          new TCCUOTARIESGO()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCCUOTARIESGO', () => {
        const returnedFromService = Object.assign(
          {
            idCuotaRiesgo: 1,
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

      it('should delete a TCCUOTARIESGO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCCUOTARIESGOToCollectionIfMissing', () => {
        it('should add a TCCUOTARIESGO to an empty array', () => {
          const tCCUOTARIESGO: ITCCUOTARIESGO = { idCuotaRiesgo: 123 };
          expectedResult = service.addTCCUOTARIESGOToCollectionIfMissing([], tCCUOTARIESGO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCUOTARIESGO);
        });

        it('should not add a TCCUOTARIESGO to an array that contains it', () => {
          const tCCUOTARIESGO: ITCCUOTARIESGO = { idCuotaRiesgo: 123 };
          const tCCUOTARIESGOCollection: ITCCUOTARIESGO[] = [
            {
              ...tCCUOTARIESGO,
            },
            { idCuotaRiesgo: 456 },
          ];
          expectedResult = service.addTCCUOTARIESGOToCollectionIfMissing(tCCUOTARIESGOCollection, tCCUOTARIESGO);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCCUOTARIESGO to an array that doesn't contain it", () => {
          const tCCUOTARIESGO: ITCCUOTARIESGO = { idCuotaRiesgo: 123 };
          const tCCUOTARIESGOCollection: ITCCUOTARIESGO[] = [{ idCuotaRiesgo: 456 }];
          expectedResult = service.addTCCUOTARIESGOToCollectionIfMissing(tCCUOTARIESGOCollection, tCCUOTARIESGO);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCUOTARIESGO);
        });

        it('should add only unique TCCUOTARIESGO to an array', () => {
          const tCCUOTARIESGOArray: ITCCUOTARIESGO[] = [{ idCuotaRiesgo: 123 }, { idCuotaRiesgo: 456 }, { idCuotaRiesgo: 57823 }];
          const tCCUOTARIESGOCollection: ITCCUOTARIESGO[] = [{ idCuotaRiesgo: 123 }];
          expectedResult = service.addTCCUOTARIESGOToCollectionIfMissing(tCCUOTARIESGOCollection, ...tCCUOTARIESGOArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCCUOTARIESGO: ITCCUOTARIESGO = { idCuotaRiesgo: 123 };
          const tCCUOTARIESGO2: ITCCUOTARIESGO = { idCuotaRiesgo: 456 };
          expectedResult = service.addTCCUOTARIESGOToCollectionIfMissing([], tCCUOTARIESGO, tCCUOTARIESGO2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCUOTARIESGO);
          expect(expectedResult).toContain(tCCUOTARIESGO2);
        });

        it('should accept null and undefined values', () => {
          const tCCUOTARIESGO: ITCCUOTARIESGO = { idCuotaRiesgo: 123 };
          expectedResult = service.addTCCUOTARIESGOToCollectionIfMissing([], null, tCCUOTARIESGO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCUOTARIESGO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
