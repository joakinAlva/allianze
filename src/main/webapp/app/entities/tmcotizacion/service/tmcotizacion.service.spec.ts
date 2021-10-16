import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ITMCOTIZACION, TMCOTIZACION } from '../tmcotizacion.model';

import { TMCOTIZACIONService } from './tmcotizacion.service';

describe('Service Tests', () => {
  describe('TMCOTIZACION Service', () => {
    let service: TMCOTIZACIONService;
    let httpMock: HttpTestingController;
    let elemDefault: ITMCOTIZACION;
    let expectedResult: ITMCOTIZACION | ITMCOTIZACION[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TMCOTIZACIONService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        idCotizacion: 0,
        usuario: 0,
        fechaRegistro: currentDate,
        estatus: 0,
        eliminada: 0,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            fechaRegistro: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TMCOTIZACION', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaRegistro: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaRegistro: currentDate,
          },
          returnedFromService
        );

        service.create(new TMCOTIZACION()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TMCOTIZACION', () => {
        const returnedFromService = Object.assign(
          {
            idCotizacion: 1,
            usuario: 1,
            fechaRegistro: currentDate.format(DATE_FORMAT),
            estatus: 1,
            eliminada: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaRegistro: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TMCOTIZACION', () => {
        const patchObject = Object.assign(
          {
            fechaRegistro: currentDate.format(DATE_FORMAT),
            eliminada: 1,
          },
          new TMCOTIZACION()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            fechaRegistro: currentDate,
          },
          returnedFromService
        );

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TMCOTIZACION', () => {
        const returnedFromService = Object.assign(
          {
            idCotizacion: 1,
            usuario: 1,
            fechaRegistro: currentDate.format(DATE_FORMAT),
            estatus: 1,
            eliminada: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaRegistro: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TMCOTIZACION', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTMCOTIZACIONToCollectionIfMissing', () => {
        it('should add a TMCOTIZACION to an empty array', () => {
          const tMCOTIZACION: ITMCOTIZACION = { idCotizacion: 123 };
          expectedResult = service.addTMCOTIZACIONToCollectionIfMissing([], tMCOTIZACION);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tMCOTIZACION);
        });

        it('should not add a TMCOTIZACION to an array that contains it', () => {
          const tMCOTIZACION: ITMCOTIZACION = { idCotizacion: 123 };
          const tMCOTIZACIONCollection: ITMCOTIZACION[] = [
            {
              ...tMCOTIZACION,
            },
            { idCotizacion: 456 },
          ];
          expectedResult = service.addTMCOTIZACIONToCollectionIfMissing(tMCOTIZACIONCollection, tMCOTIZACION);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TMCOTIZACION to an array that doesn't contain it", () => {
          const tMCOTIZACION: ITMCOTIZACION = { idCotizacion: 123 };
          const tMCOTIZACIONCollection: ITMCOTIZACION[] = [{ idCotizacion: 456 }];
          expectedResult = service.addTMCOTIZACIONToCollectionIfMissing(tMCOTIZACIONCollection, tMCOTIZACION);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tMCOTIZACION);
        });

        it('should add only unique TMCOTIZACION to an array', () => {
          const tMCOTIZACIONArray: ITMCOTIZACION[] = [{ idCotizacion: 123 }, { idCotizacion: 456 }, { idCotizacion: 47693 }];
          const tMCOTIZACIONCollection: ITMCOTIZACION[] = [{ idCotizacion: 123 }];
          expectedResult = service.addTMCOTIZACIONToCollectionIfMissing(tMCOTIZACIONCollection, ...tMCOTIZACIONArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tMCOTIZACION: ITMCOTIZACION = { idCotizacion: 123 };
          const tMCOTIZACION2: ITMCOTIZACION = { idCotizacion: 456 };
          expectedResult = service.addTMCOTIZACIONToCollectionIfMissing([], tMCOTIZACION, tMCOTIZACION2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tMCOTIZACION);
          expect(expectedResult).toContain(tMCOTIZACION2);
        });

        it('should accept null and undefined values', () => {
          const tMCOTIZACION: ITMCOTIZACION = { idCotizacion: 123 };
          expectedResult = service.addTMCOTIZACIONToCollectionIfMissing([], null, tMCOTIZACION, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tMCOTIZACION);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
