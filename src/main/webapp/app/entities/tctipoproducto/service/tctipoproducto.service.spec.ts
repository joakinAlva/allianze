import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ITCTIPOPRODUCTO, TCTIPOPRODUCTO } from '../tctipoproducto.model';

import { TCTIPOPRODUCTOService } from './tctipoproducto.service';

describe('Service Tests', () => {
  describe('TCTIPOPRODUCTO Service', () => {
    let service: TCTIPOPRODUCTOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCTIPOPRODUCTO;
    let expectedResult: ITCTIPOPRODUCTO | ITCTIPOPRODUCTO[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCTIPOPRODUCTOService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        idTipoProducto: 0,
        tipoProducto: 'AAAAAAA',
        registro: 'AAAAAAA',
        fecha: currentDate,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            fecha: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TCTIPOPRODUCTO', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fecha: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fecha: currentDate,
          },
          returnedFromService
        );

        service.create(new TCTIPOPRODUCTO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCTIPOPRODUCTO', () => {
        const returnedFromService = Object.assign(
          {
            idTipoProducto: 1,
            tipoProducto: 'BBBBBB',
            registro: 'BBBBBB',
            fecha: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fecha: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCTIPOPRODUCTO', () => {
        const patchObject = Object.assign(
          {
            registro: 'BBBBBB',
          },
          new TCTIPOPRODUCTO()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            fecha: currentDate,
          },
          returnedFromService
        );

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCTIPOPRODUCTO', () => {
        const returnedFromService = Object.assign(
          {
            idTipoProducto: 1,
            tipoProducto: 'BBBBBB',
            registro: 'BBBBBB',
            fecha: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fecha: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TCTIPOPRODUCTO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCTIPOPRODUCTOToCollectionIfMissing', () => {
        it('should add a TCTIPOPRODUCTO to an empty array', () => {
          const tCTIPOPRODUCTO: ITCTIPOPRODUCTO = { idTipoProducto: 123 };
          expectedResult = service.addTCTIPOPRODUCTOToCollectionIfMissing([], tCTIPOPRODUCTO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCTIPOPRODUCTO);
        });

        it('should not add a TCTIPOPRODUCTO to an array that contains it', () => {
          const tCTIPOPRODUCTO: ITCTIPOPRODUCTO = { idTipoProducto: 123 };
          const tCTIPOPRODUCTOCollection: ITCTIPOPRODUCTO[] = [
            {
              ...tCTIPOPRODUCTO,
            },
            { idTipoProducto: 456 },
          ];
          expectedResult = service.addTCTIPOPRODUCTOToCollectionIfMissing(tCTIPOPRODUCTOCollection, tCTIPOPRODUCTO);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCTIPOPRODUCTO to an array that doesn't contain it", () => {
          const tCTIPOPRODUCTO: ITCTIPOPRODUCTO = { idTipoProducto: 123 };
          const tCTIPOPRODUCTOCollection: ITCTIPOPRODUCTO[] = [{ idTipoProducto: 456 }];
          expectedResult = service.addTCTIPOPRODUCTOToCollectionIfMissing(tCTIPOPRODUCTOCollection, tCTIPOPRODUCTO);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCTIPOPRODUCTO);
        });

        it('should add only unique TCTIPOPRODUCTO to an array', () => {
          const tCTIPOPRODUCTOArray: ITCTIPOPRODUCTO[] = [{ idTipoProducto: 123 }, { idTipoProducto: 456 }, { idTipoProducto: 16138 }];
          const tCTIPOPRODUCTOCollection: ITCTIPOPRODUCTO[] = [{ idTipoProducto: 123 }];
          expectedResult = service.addTCTIPOPRODUCTOToCollectionIfMissing(tCTIPOPRODUCTOCollection, ...tCTIPOPRODUCTOArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCTIPOPRODUCTO: ITCTIPOPRODUCTO = { idTipoProducto: 123 };
          const tCTIPOPRODUCTO2: ITCTIPOPRODUCTO = { idTipoProducto: 456 };
          expectedResult = service.addTCTIPOPRODUCTOToCollectionIfMissing([], tCTIPOPRODUCTO, tCTIPOPRODUCTO2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCTIPOPRODUCTO);
          expect(expectedResult).toContain(tCTIPOPRODUCTO2);
        });

        it('should accept null and undefined values', () => {
          const tCTIPOPRODUCTO: ITCTIPOPRODUCTO = { idTipoProducto: 123 };
          expectedResult = service.addTCTIPOPRODUCTOToCollectionIfMissing([], null, tCTIPOPRODUCTO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCTIPOPRODUCTO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
