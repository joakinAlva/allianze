import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ITMASEGURADO, TMASEGURADO } from '../tmasegurado.model';

import { TMASEGURADOService } from './tmasegurado.service';

describe('Service Tests', () => {
  describe('TMASEGURADO Service', () => {
    let service: TMASEGURADOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITMASEGURADO;
    let expectedResult: ITMASEGURADO | ITMASEGURADO[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TMASEGURADOService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        idAsegurados: 0,
        numero: 0,
        excedente: 0,
        subgrupo: 0,
        nombre: 'AAAAAAA',
        fNacimiento: currentDate,
        sueldo: 0,
        reglaMonto: 0,
        edad: 0,
        saTotal: 0,
        saTopado: 0,
        basica: 0,
        basicaCovid: 0,
        extrabas: 0,
        primaBasica: 0,
        invalidez: 0,
        extraInv: 0,
        exencion: 0,
        extraExe: 0,
        muerteAcc: 0,
        extraAcc: 0,
        valorGff: 0,
        valorGffCovid: 0,
        extraGff: 0,
        primaGff: 0,
        primaCa: 0,
        extraCa: 0,
        primaGe: 0,
        extraGe: 0,
        primaIqs: 0,
        extraIqa: 0,
        primaIqv: 0,
        extraIqv: 0,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            fNacimiento: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TMASEGURADO', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fNacimiento: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fNacimiento: currentDate,
          },
          returnedFromService
        );

        service.create(new TMASEGURADO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TMASEGURADO', () => {
        const returnedFromService = Object.assign(
          {
            idAsegurados: 1,
            numero: 1,
            excedente: 1,
            subgrupo: 1,
            nombre: 'BBBBBB',
            fNacimiento: currentDate.format(DATE_FORMAT),
            sueldo: 1,
            reglaMonto: 1,
            edad: 1,
            saTotal: 1,
            saTopado: 1,
            basica: 1,
            basicaCovid: 1,
            extrabas: 1,
            primaBasica: 1,
            invalidez: 1,
            extraInv: 1,
            exencion: 1,
            extraExe: 1,
            muerteAcc: 1,
            extraAcc: 1,
            valorGff: 1,
            valorGffCovid: 1,
            extraGff: 1,
            primaGff: 1,
            primaCa: 1,
            extraCa: 1,
            primaGe: 1,
            extraGe: 1,
            primaIqs: 1,
            extraIqa: 1,
            primaIqv: 1,
            extraIqv: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fNacimiento: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TMASEGURADO', () => {
        const patchObject = Object.assign(
          {
            numero: 1,
            nombre: 'BBBBBB',
            sueldo: 1,
            reglaMonto: 1,
            saTopado: 1,
            extrabas: 1,
            primaBasica: 1,
            extraInv: 1,
            extraExe: 1,
            extraAcc: 1,
            valorGff: 1,
            valorGffCovid: 1,
            primaGff: 1,
            extraCa: 1,
            extraGe: 1,
            primaIqs: 1,
            extraIqa: 1,
            extraIqv: 1,
          },
          new TMASEGURADO()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            fNacimiento: currentDate,
          },
          returnedFromService
        );

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TMASEGURADO', () => {
        const returnedFromService = Object.assign(
          {
            idAsegurados: 1,
            numero: 1,
            excedente: 1,
            subgrupo: 1,
            nombre: 'BBBBBB',
            fNacimiento: currentDate.format(DATE_FORMAT),
            sueldo: 1,
            reglaMonto: 1,
            edad: 1,
            saTotal: 1,
            saTopado: 1,
            basica: 1,
            basicaCovid: 1,
            extrabas: 1,
            primaBasica: 1,
            invalidez: 1,
            extraInv: 1,
            exencion: 1,
            extraExe: 1,
            muerteAcc: 1,
            extraAcc: 1,
            valorGff: 1,
            valorGffCovid: 1,
            extraGff: 1,
            primaGff: 1,
            primaCa: 1,
            extraCa: 1,
            primaGe: 1,
            extraGe: 1,
            primaIqs: 1,
            extraIqa: 1,
            primaIqv: 1,
            extraIqv: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fNacimiento: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TMASEGURADO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTMASEGURADOToCollectionIfMissing', () => {
        it('should add a TMASEGURADO to an empty array', () => {
          const tMASEGURADO: ITMASEGURADO = { idAsegurados: 123 };
          expectedResult = service.addTMASEGURADOToCollectionIfMissing([], tMASEGURADO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tMASEGURADO);
        });

        it('should not add a TMASEGURADO to an array that contains it', () => {
          const tMASEGURADO: ITMASEGURADO = { idAsegurados: 123 };
          const tMASEGURADOCollection: ITMASEGURADO[] = [
            {
              ...tMASEGURADO,
            },
            { idAsegurados: 456 },
          ];
          expectedResult = service.addTMASEGURADOToCollectionIfMissing(tMASEGURADOCollection, tMASEGURADO);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TMASEGURADO to an array that doesn't contain it", () => {
          const tMASEGURADO: ITMASEGURADO = { idAsegurados: 123 };
          const tMASEGURADOCollection: ITMASEGURADO[] = [{ idAsegurados: 456 }];
          expectedResult = service.addTMASEGURADOToCollectionIfMissing(tMASEGURADOCollection, tMASEGURADO);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tMASEGURADO);
        });

        it('should add only unique TMASEGURADO to an array', () => {
          const tMASEGURADOArray: ITMASEGURADO[] = [{ idAsegurados: 123 }, { idAsegurados: 456 }, { idAsegurados: 99486 }];
          const tMASEGURADOCollection: ITMASEGURADO[] = [{ idAsegurados: 123 }];
          expectedResult = service.addTMASEGURADOToCollectionIfMissing(tMASEGURADOCollection, ...tMASEGURADOArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tMASEGURADO: ITMASEGURADO = { idAsegurados: 123 };
          const tMASEGURADO2: ITMASEGURADO = { idAsegurados: 456 };
          expectedResult = service.addTMASEGURADOToCollectionIfMissing([], tMASEGURADO, tMASEGURADO2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tMASEGURADO);
          expect(expectedResult).toContain(tMASEGURADO2);
        });

        it('should accept null and undefined values', () => {
          const tMASEGURADO: ITMASEGURADO = { idAsegurados: 123 };
          expectedResult = service.addTMASEGURADOToCollectionIfMissing([], null, tMASEGURADO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tMASEGURADO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
