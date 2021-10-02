import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCPERFIL, TCPERFIL } from '../tcperfil.model';

import { TCPERFILService } from './tcperfil.service';

describe('Service Tests', () => {
  describe('TCPERFIL Service', () => {
    let service: TCPERFILService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCPERFIL;
    let expectedResult: ITCPERFIL | ITCPERFIL[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCPERFILService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idPerfil: 0,
        perfil: 'AAAAAAA',
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

      it('should create a TCPERFIL', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCPERFIL()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCPERFIL', () => {
        const returnedFromService = Object.assign(
          {
            idPerfil: 1,
            perfil: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCPERFIL', () => {
        const patchObject = Object.assign(
          {
            perfil: 'BBBBBB',
          },
          new TCPERFIL()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCPERFIL', () => {
        const returnedFromService = Object.assign(
          {
            idPerfil: 1,
            perfil: 'BBBBBB',
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

      it('should delete a TCPERFIL', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCPERFILToCollectionIfMissing', () => {
        it('should add a TCPERFIL to an empty array', () => {
          const tCPERFIL: ITCPERFIL = { idPerfil: 123 };
          expectedResult = service.addTCPERFILToCollectionIfMissing([], tCPERFIL);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCPERFIL);
        });

        it('should not add a TCPERFIL to an array that contains it', () => {
          const tCPERFIL: ITCPERFIL = { idPerfil: 123 };
          const tCPERFILCollection: ITCPERFIL[] = [
            {
              ...tCPERFIL,
            },
            { idPerfil: 456 },
          ];
          expectedResult = service.addTCPERFILToCollectionIfMissing(tCPERFILCollection, tCPERFIL);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCPERFIL to an array that doesn't contain it", () => {
          const tCPERFIL: ITCPERFIL = { idPerfil: 123 };
          const tCPERFILCollection: ITCPERFIL[] = [{ idPerfil: 456 }];
          expectedResult = service.addTCPERFILToCollectionIfMissing(tCPERFILCollection, tCPERFIL);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCPERFIL);
        });

        it('should add only unique TCPERFIL to an array', () => {
          const tCPERFILArray: ITCPERFIL[] = [{ idPerfil: 123 }, { idPerfil: 456 }, { idPerfil: 95398 }];
          const tCPERFILCollection: ITCPERFIL[] = [{ idPerfil: 123 }];
          expectedResult = service.addTCPERFILToCollectionIfMissing(tCPERFILCollection, ...tCPERFILArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCPERFIL: ITCPERFIL = { idPerfil: 123 };
          const tCPERFIL2: ITCPERFIL = { idPerfil: 456 };
          expectedResult = service.addTCPERFILToCollectionIfMissing([], tCPERFIL, tCPERFIL2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCPERFIL);
          expect(expectedResult).toContain(tCPERFIL2);
        });

        it('should accept null and undefined values', () => {
          const tCPERFIL: ITCPERFIL = { idPerfil: 123 };
          expectedResult = service.addTCPERFILToCollectionIfMissing([], null, tCPERFIL, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCPERFIL);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
