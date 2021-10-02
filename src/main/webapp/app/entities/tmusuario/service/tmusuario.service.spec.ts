import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ITMUSUARIO, TMUSUARIO } from '../tmusuario.model';

import { TMUSUARIOService } from './tmusuario.service';

describe('Service Tests', () => {
  describe('TMUSUARIO Service', () => {
    let service: TMUSUARIOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITMUSUARIO;
    let expectedResult: ITMUSUARIO | ITMUSUARIO[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TMUSUARIOService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        idUsuario: 0,
        nombre: 'AAAAAAA',
        apellidos: 'AAAAAAA',
        correoElectronico: 'AAAAAAA',
        usuario: 'AAAAAAA',
        clave: 'AAAAAAA',
        fechaRegistro: currentDate,
        token: 'AAAAAAA',
        estatus: 0,
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

      it('should create a TMUSUARIO', () => {
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

        service.create(new TMUSUARIO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TMUSUARIO', () => {
        const returnedFromService = Object.assign(
          {
            idUsuario: 1,
            nombre: 'BBBBBB',
            apellidos: 'BBBBBB',
            correoElectronico: 'BBBBBB',
            usuario: 'BBBBBB',
            clave: 'BBBBBB',
            fechaRegistro: currentDate.format(DATE_FORMAT),
            token: 'BBBBBB',
            estatus: 1,
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

      it('should partial update a TMUSUARIO', () => {
        const patchObject = Object.assign(
          {
            usuario: 'BBBBBB',
            token: 'BBBBBB',
            estatus: 1,
          },
          new TMUSUARIO()
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

      it('should return a list of TMUSUARIO', () => {
        const returnedFromService = Object.assign(
          {
            idUsuario: 1,
            nombre: 'BBBBBB',
            apellidos: 'BBBBBB',
            correoElectronico: 'BBBBBB',
            usuario: 'BBBBBB',
            clave: 'BBBBBB',
            fechaRegistro: currentDate.format(DATE_FORMAT),
            token: 'BBBBBB',
            estatus: 1,
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

      it('should delete a TMUSUARIO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTMUSUARIOToCollectionIfMissing', () => {
        it('should add a TMUSUARIO to an empty array', () => {
          const tMUSUARIO: ITMUSUARIO = { idUsuario: 123 };
          expectedResult = service.addTMUSUARIOToCollectionIfMissing([], tMUSUARIO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tMUSUARIO);
        });

        it('should not add a TMUSUARIO to an array that contains it', () => {
          const tMUSUARIO: ITMUSUARIO = { idUsuario: 123 };
          const tMUSUARIOCollection: ITMUSUARIO[] = [
            {
              ...tMUSUARIO,
            },
            { idUsuario: 456 },
          ];
          expectedResult = service.addTMUSUARIOToCollectionIfMissing(tMUSUARIOCollection, tMUSUARIO);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TMUSUARIO to an array that doesn't contain it", () => {
          const tMUSUARIO: ITMUSUARIO = { idUsuario: 123 };
          const tMUSUARIOCollection: ITMUSUARIO[] = [{ idUsuario: 456 }];
          expectedResult = service.addTMUSUARIOToCollectionIfMissing(tMUSUARIOCollection, tMUSUARIO);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tMUSUARIO);
        });

        it('should add only unique TMUSUARIO to an array', () => {
          const tMUSUARIOArray: ITMUSUARIO[] = [{ idUsuario: 123 }, { idUsuario: 456 }, { idUsuario: 80448 }];
          const tMUSUARIOCollection: ITMUSUARIO[] = [{ idUsuario: 123 }];
          expectedResult = service.addTMUSUARIOToCollectionIfMissing(tMUSUARIOCollection, ...tMUSUARIOArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tMUSUARIO: ITMUSUARIO = { idUsuario: 123 };
          const tMUSUARIO2: ITMUSUARIO = { idUsuario: 456 };
          expectedResult = service.addTMUSUARIOToCollectionIfMissing([], tMUSUARIO, tMUSUARIO2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tMUSUARIO);
          expect(expectedResult).toContain(tMUSUARIO2);
        });

        it('should accept null and undefined values', () => {
          const tMUSUARIO: ITMUSUARIO = { idUsuario: 123 };
          expectedResult = service.addTMUSUARIOToCollectionIfMissing([], null, tMUSUARIO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tMUSUARIO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
