import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCPRIMANETASDESC, TCPRIMANETASDESC } from '../tcprimanetasdesc.model';

import { TCPRIMANETASDESCService } from './tcprimanetasdesc.service';

describe('Service Tests', () => {
  describe('TCPRIMANETASDESC Service', () => {
    let service: TCPRIMANETASDESCService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCPRIMANETASDESC;
    let expectedResult: ITCPRIMANETASDESC | ITCPRIMANETASDESC[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCPRIMANETASDESCService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idPrimaNetaSdesc: 0,
        primaNetaSdesc: 'AAAAAAA',
        margenMin: 0,
        margenMax: 0,
        maxComSd: 0,
        maxComEp: 0,
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

      it('should create a TCPRIMANETASDESC', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCPRIMANETASDESC()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCPRIMANETASDESC', () => {
        const returnedFromService = Object.assign(
          {
            idPrimaNetaSdesc: 1,
            primaNetaSdesc: 'BBBBBB',
            margenMin: 1,
            margenMax: 1,
            maxComSd: 1,
            maxComEp: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCPRIMANETASDESC', () => {
        const patchObject = Object.assign(
          {
            primaNetaSdesc: 'BBBBBB',
            margenMax: 1,
            maxComSd: 1,
            maxComEp: 1,
          },
          new TCPRIMANETASDESC()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCPRIMANETASDESC', () => {
        const returnedFromService = Object.assign(
          {
            idPrimaNetaSdesc: 1,
            primaNetaSdesc: 'BBBBBB',
            margenMin: 1,
            margenMax: 1,
            maxComSd: 1,
            maxComEp: 1,
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

      it('should delete a TCPRIMANETASDESC', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCPRIMANETASDESCToCollectionIfMissing', () => {
        it('should add a TCPRIMANETASDESC to an empty array', () => {
          const tCPRIMANETASDESC: ITCPRIMANETASDESC = { idPrimaNetaSdesc: 123 };
          expectedResult = service.addTCPRIMANETASDESCToCollectionIfMissing([], tCPRIMANETASDESC);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCPRIMANETASDESC);
        });

        it('should not add a TCPRIMANETASDESC to an array that contains it', () => {
          const tCPRIMANETASDESC: ITCPRIMANETASDESC = { idPrimaNetaSdesc: 123 };
          const tCPRIMANETASDESCCollection: ITCPRIMANETASDESC[] = [
            {
              ...tCPRIMANETASDESC,
            },
            { idPrimaNetaSdesc: 456 },
          ];
          expectedResult = service.addTCPRIMANETASDESCToCollectionIfMissing(tCPRIMANETASDESCCollection, tCPRIMANETASDESC);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCPRIMANETASDESC to an array that doesn't contain it", () => {
          const tCPRIMANETASDESC: ITCPRIMANETASDESC = { idPrimaNetaSdesc: 123 };
          const tCPRIMANETASDESCCollection: ITCPRIMANETASDESC[] = [{ idPrimaNetaSdesc: 456 }];
          expectedResult = service.addTCPRIMANETASDESCToCollectionIfMissing(tCPRIMANETASDESCCollection, tCPRIMANETASDESC);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCPRIMANETASDESC);
        });

        it('should add only unique TCPRIMANETASDESC to an array', () => {
          const tCPRIMANETASDESCArray: ITCPRIMANETASDESC[] = [
            { idPrimaNetaSdesc: 123 },
            { idPrimaNetaSdesc: 456 },
            { idPrimaNetaSdesc: 70544 },
          ];
          const tCPRIMANETASDESCCollection: ITCPRIMANETASDESC[] = [{ idPrimaNetaSdesc: 123 }];
          expectedResult = service.addTCPRIMANETASDESCToCollectionIfMissing(tCPRIMANETASDESCCollection, ...tCPRIMANETASDESCArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCPRIMANETASDESC: ITCPRIMANETASDESC = { idPrimaNetaSdesc: 123 };
          const tCPRIMANETASDESC2: ITCPRIMANETASDESC = { idPrimaNetaSdesc: 456 };
          expectedResult = service.addTCPRIMANETASDESCToCollectionIfMissing([], tCPRIMANETASDESC, tCPRIMANETASDESC2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCPRIMANETASDESC);
          expect(expectedResult).toContain(tCPRIMANETASDESC2);
        });

        it('should accept null and undefined values', () => {
          const tCPRIMANETASDESC: ITCPRIMANETASDESC = { idPrimaNetaSdesc: 123 };
          expectedResult = service.addTCPRIMANETASDESCToCollectionIfMissing([], null, tCPRIMANETASDESC, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCPRIMANETASDESC);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
