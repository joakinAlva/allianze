import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCUNIDADNEGOCIO, TCUNIDADNEGOCIO } from '../tcunidadnegocio.model';

import { TCUNIDADNEGOCIOService } from './tcunidadnegocio.service';

describe('Service Tests', () => {
  describe('TCUNIDADNEGOCIO Service', () => {
    let service: TCUNIDADNEGOCIOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCUNIDADNEGOCIO;
    let expectedResult: ITCUNIDADNEGOCIO | ITCUNIDADNEGOCIO[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCUNIDADNEGOCIOService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idUnidadNegocio: 0,
        unidadNegocio: 'AAAAAAA',
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

      it('should create a TCUNIDADNEGOCIO', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCUNIDADNEGOCIO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCUNIDADNEGOCIO', () => {
        const returnedFromService = Object.assign(
          {
            idUnidadNegocio: 1,
            unidadNegocio: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCUNIDADNEGOCIO', () => {
        const patchObject = Object.assign({}, new TCUNIDADNEGOCIO());

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCUNIDADNEGOCIO', () => {
        const returnedFromService = Object.assign(
          {
            idUnidadNegocio: 1,
            unidadNegocio: 'BBBBBB',
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

      it('should delete a TCUNIDADNEGOCIO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCUNIDADNEGOCIOToCollectionIfMissing', () => {
        it('should add a TCUNIDADNEGOCIO to an empty array', () => {
          const tCUNIDADNEGOCIO: ITCUNIDADNEGOCIO = { idUnidadNegocio: 123 };
          expectedResult = service.addTCUNIDADNEGOCIOToCollectionIfMissing([], tCUNIDADNEGOCIO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCUNIDADNEGOCIO);
        });

        it('should not add a TCUNIDADNEGOCIO to an array that contains it', () => {
          const tCUNIDADNEGOCIO: ITCUNIDADNEGOCIO = { idUnidadNegocio: 123 };
          const tCUNIDADNEGOCIOCollection: ITCUNIDADNEGOCIO[] = [
            {
              ...tCUNIDADNEGOCIO,
            },
            { idUnidadNegocio: 456 },
          ];
          expectedResult = service.addTCUNIDADNEGOCIOToCollectionIfMissing(tCUNIDADNEGOCIOCollection, tCUNIDADNEGOCIO);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCUNIDADNEGOCIO to an array that doesn't contain it", () => {
          const tCUNIDADNEGOCIO: ITCUNIDADNEGOCIO = { idUnidadNegocio: 123 };
          const tCUNIDADNEGOCIOCollection: ITCUNIDADNEGOCIO[] = [{ idUnidadNegocio: 456 }];
          expectedResult = service.addTCUNIDADNEGOCIOToCollectionIfMissing(tCUNIDADNEGOCIOCollection, tCUNIDADNEGOCIO);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCUNIDADNEGOCIO);
        });

        it('should add only unique TCUNIDADNEGOCIO to an array', () => {
          const tCUNIDADNEGOCIOArray: ITCUNIDADNEGOCIO[] = [{ idUnidadNegocio: 123 }, { idUnidadNegocio: 456 }, { idUnidadNegocio: 42910 }];
          const tCUNIDADNEGOCIOCollection: ITCUNIDADNEGOCIO[] = [{ idUnidadNegocio: 123 }];
          expectedResult = service.addTCUNIDADNEGOCIOToCollectionIfMissing(tCUNIDADNEGOCIOCollection, ...tCUNIDADNEGOCIOArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCUNIDADNEGOCIO: ITCUNIDADNEGOCIO = { idUnidadNegocio: 123 };
          const tCUNIDADNEGOCIO2: ITCUNIDADNEGOCIO = { idUnidadNegocio: 456 };
          expectedResult = service.addTCUNIDADNEGOCIOToCollectionIfMissing([], tCUNIDADNEGOCIO, tCUNIDADNEGOCIO2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCUNIDADNEGOCIO);
          expect(expectedResult).toContain(tCUNIDADNEGOCIO2);
        });

        it('should accept null and undefined values', () => {
          const tCUNIDADNEGOCIO: ITCUNIDADNEGOCIO = { idUnidadNegocio: 123 };
          expectedResult = service.addTCUNIDADNEGOCIOToCollectionIfMissing([], null, tCUNIDADNEGOCIO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCUNIDADNEGOCIO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
