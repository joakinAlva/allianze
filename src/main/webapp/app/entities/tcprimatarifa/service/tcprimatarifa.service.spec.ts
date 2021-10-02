import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCPRIMATARIFA, TCPRIMATARIFA } from '../tcprimatarifa.model';

import { TCPRIMATARIFAService } from './tcprimatarifa.service';

describe('Service Tests', () => {
  describe('TCPRIMATARIFA Service', () => {
    let service: TCPRIMATARIFAService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCPRIMATARIFA;
    let expectedResult: ITCPRIMATARIFA | ITCPRIMATARIFA[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCPRIMATARIFAService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idPrimaTarifa: 0,
        divPrimaTarifa: 0,
        zNeta: 0,
        divPrimaRiesgo: 0,
        zRiesgo: 0,
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

      it('should create a TCPRIMATARIFA', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCPRIMATARIFA()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCPRIMATARIFA', () => {
        const returnedFromService = Object.assign(
          {
            idPrimaTarifa: 1,
            divPrimaTarifa: 1,
            zNeta: 1,
            divPrimaRiesgo: 1,
            zRiesgo: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCPRIMATARIFA', () => {
        const patchObject = Object.assign(
          {
            divPrimaRiesgo: 1,
            zRiesgo: 1,
          },
          new TCPRIMATARIFA()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCPRIMATARIFA', () => {
        const returnedFromService = Object.assign(
          {
            idPrimaTarifa: 1,
            divPrimaTarifa: 1,
            zNeta: 1,
            divPrimaRiesgo: 1,
            zRiesgo: 1,
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

      it('should delete a TCPRIMATARIFA', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCPRIMATARIFAToCollectionIfMissing', () => {
        it('should add a TCPRIMATARIFA to an empty array', () => {
          const tCPRIMATARIFA: ITCPRIMATARIFA = { idPrimaTarifa: 123 };
          expectedResult = service.addTCPRIMATARIFAToCollectionIfMissing([], tCPRIMATARIFA);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCPRIMATARIFA);
        });

        it('should not add a TCPRIMATARIFA to an array that contains it', () => {
          const tCPRIMATARIFA: ITCPRIMATARIFA = { idPrimaTarifa: 123 };
          const tCPRIMATARIFACollection: ITCPRIMATARIFA[] = [
            {
              ...tCPRIMATARIFA,
            },
            { idPrimaTarifa: 456 },
          ];
          expectedResult = service.addTCPRIMATARIFAToCollectionIfMissing(tCPRIMATARIFACollection, tCPRIMATARIFA);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCPRIMATARIFA to an array that doesn't contain it", () => {
          const tCPRIMATARIFA: ITCPRIMATARIFA = { idPrimaTarifa: 123 };
          const tCPRIMATARIFACollection: ITCPRIMATARIFA[] = [{ idPrimaTarifa: 456 }];
          expectedResult = service.addTCPRIMATARIFAToCollectionIfMissing(tCPRIMATARIFACollection, tCPRIMATARIFA);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCPRIMATARIFA);
        });

        it('should add only unique TCPRIMATARIFA to an array', () => {
          const tCPRIMATARIFAArray: ITCPRIMATARIFA[] = [{ idPrimaTarifa: 123 }, { idPrimaTarifa: 456 }, { idPrimaTarifa: 25435 }];
          const tCPRIMATARIFACollection: ITCPRIMATARIFA[] = [{ idPrimaTarifa: 123 }];
          expectedResult = service.addTCPRIMATARIFAToCollectionIfMissing(tCPRIMATARIFACollection, ...tCPRIMATARIFAArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCPRIMATARIFA: ITCPRIMATARIFA = { idPrimaTarifa: 123 };
          const tCPRIMATARIFA2: ITCPRIMATARIFA = { idPrimaTarifa: 456 };
          expectedResult = service.addTCPRIMATARIFAToCollectionIfMissing([], tCPRIMATARIFA, tCPRIMATARIFA2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCPRIMATARIFA);
          expect(expectedResult).toContain(tCPRIMATARIFA2);
        });

        it('should accept null and undefined values', () => {
          const tCPRIMATARIFA: ITCPRIMATARIFA = { idPrimaTarifa: 123 };
          expectedResult = service.addTCPRIMATARIFAToCollectionIfMissing([], null, tCPRIMATARIFA, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCPRIMATARIFA);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
