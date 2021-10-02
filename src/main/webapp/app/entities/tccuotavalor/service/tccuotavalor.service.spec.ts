import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCCUOTAVALOR, TCCUOTAVALOR } from '../tccuotavalor.model';

import { TCCUOTAVALORService } from './tccuotavalor.service';

describe('Service Tests', () => {
  describe('TCCUOTAVALOR Service', () => {
    let service: TCCUOTAVALORService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCCUOTAVALOR;
    let expectedResult: ITCCUOTAVALOR | ITCCUOTAVALOR[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCCUOTAVALORService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idCuotaValor: 0,
        porcentaje: 0,
        valor: 'AAAAAAA',
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

      it('should create a TCCUOTAVALOR', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCCUOTAVALOR()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCCUOTAVALOR', () => {
        const returnedFromService = Object.assign(
          {
            idCuotaValor: 1,
            porcentaje: 1,
            valor: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCCUOTAVALOR', () => {
        const patchObject = Object.assign(
          {
            porcentaje: 1,
            valor: 'BBBBBB',
          },
          new TCCUOTAVALOR()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCCUOTAVALOR', () => {
        const returnedFromService = Object.assign(
          {
            idCuotaValor: 1,
            porcentaje: 1,
            valor: 'BBBBBB',
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

      it('should delete a TCCUOTAVALOR', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCCUOTAVALORToCollectionIfMissing', () => {
        it('should add a TCCUOTAVALOR to an empty array', () => {
          const tCCUOTAVALOR: ITCCUOTAVALOR = { idCuotaValor: 123 };
          expectedResult = service.addTCCUOTAVALORToCollectionIfMissing([], tCCUOTAVALOR);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCUOTAVALOR);
        });

        it('should not add a TCCUOTAVALOR to an array that contains it', () => {
          const tCCUOTAVALOR: ITCCUOTAVALOR = { idCuotaValor: 123 };
          const tCCUOTAVALORCollection: ITCCUOTAVALOR[] = [
            {
              ...tCCUOTAVALOR,
            },
            { idCuotaValor: 456 },
          ];
          expectedResult = service.addTCCUOTAVALORToCollectionIfMissing(tCCUOTAVALORCollection, tCCUOTAVALOR);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCCUOTAVALOR to an array that doesn't contain it", () => {
          const tCCUOTAVALOR: ITCCUOTAVALOR = { idCuotaValor: 123 };
          const tCCUOTAVALORCollection: ITCCUOTAVALOR[] = [{ idCuotaValor: 456 }];
          expectedResult = service.addTCCUOTAVALORToCollectionIfMissing(tCCUOTAVALORCollection, tCCUOTAVALOR);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCUOTAVALOR);
        });

        it('should add only unique TCCUOTAVALOR to an array', () => {
          const tCCUOTAVALORArray: ITCCUOTAVALOR[] = [{ idCuotaValor: 123 }, { idCuotaValor: 456 }, { idCuotaValor: 95201 }];
          const tCCUOTAVALORCollection: ITCCUOTAVALOR[] = [{ idCuotaValor: 123 }];
          expectedResult = service.addTCCUOTAVALORToCollectionIfMissing(tCCUOTAVALORCollection, ...tCCUOTAVALORArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCCUOTAVALOR: ITCCUOTAVALOR = { idCuotaValor: 123 };
          const tCCUOTAVALOR2: ITCCUOTAVALOR = { idCuotaValor: 456 };
          expectedResult = service.addTCCUOTAVALORToCollectionIfMissing([], tCCUOTAVALOR, tCCUOTAVALOR2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCUOTAVALOR);
          expect(expectedResult).toContain(tCCUOTAVALOR2);
        });

        it('should accept null and undefined values', () => {
          const tCCUOTAVALOR: ITCCUOTAVALOR = { idCuotaValor: 123 };
          expectedResult = service.addTCCUOTAVALORToCollectionIfMissing([], null, tCCUOTAVALOR, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCUOTAVALOR);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
