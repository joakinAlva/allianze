import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCREGIONAL, TCREGIONAL } from '../tcregional.model';

import { TCREGIONALService } from './tcregional.service';

describe('Service Tests', () => {
  describe('TCREGIONAL Service', () => {
    let service: TCREGIONALService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCREGIONAL;
    let expectedResult: ITCREGIONAL | ITCREGIONAL[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCREGIONALService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idRegional: 0,
        regional: 'AAAAAAA',
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

      it('should create a TCREGIONAL', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCREGIONAL()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCREGIONAL', () => {
        const returnedFromService = Object.assign(
          {
            idRegional: 1,
            regional: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCREGIONAL', () => {
        const patchObject = Object.assign({}, new TCREGIONAL());

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCREGIONAL', () => {
        const returnedFromService = Object.assign(
          {
            idRegional: 1,
            regional: 'BBBBBB',
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

      it('should delete a TCREGIONAL', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCREGIONALToCollectionIfMissing', () => {
        it('should add a TCREGIONAL to an empty array', () => {
          const tCREGIONAL: ITCREGIONAL = { idRegional: 123 };
          expectedResult = service.addTCREGIONALToCollectionIfMissing([], tCREGIONAL);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCREGIONAL);
        });

        it('should not add a TCREGIONAL to an array that contains it', () => {
          const tCREGIONAL: ITCREGIONAL = { idRegional: 123 };
          const tCREGIONALCollection: ITCREGIONAL[] = [
            {
              ...tCREGIONAL,
            },
            { idRegional: 456 },
          ];
          expectedResult = service.addTCREGIONALToCollectionIfMissing(tCREGIONALCollection, tCREGIONAL);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCREGIONAL to an array that doesn't contain it", () => {
          const tCREGIONAL: ITCREGIONAL = { idRegional: 123 };
          const tCREGIONALCollection: ITCREGIONAL[] = [{ idRegional: 456 }];
          expectedResult = service.addTCREGIONALToCollectionIfMissing(tCREGIONALCollection, tCREGIONAL);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCREGIONAL);
        });

        it('should add only unique TCREGIONAL to an array', () => {
          const tCREGIONALArray: ITCREGIONAL[] = [{ idRegional: 123 }, { idRegional: 456 }, { idRegional: 16153 }];
          const tCREGIONALCollection: ITCREGIONAL[] = [{ idRegional: 123 }];
          expectedResult = service.addTCREGIONALToCollectionIfMissing(tCREGIONALCollection, ...tCREGIONALArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCREGIONAL: ITCREGIONAL = { idRegional: 123 };
          const tCREGIONAL2: ITCREGIONAL = { idRegional: 456 };
          expectedResult = service.addTCREGIONALToCollectionIfMissing([], tCREGIONAL, tCREGIONAL2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCREGIONAL);
          expect(expectedResult).toContain(tCREGIONAL2);
        });

        it('should accept null and undefined values', () => {
          const tCREGIONAL: ITCREGIONAL = { idRegional: 123 };
          expectedResult = service.addTCREGIONALToCollectionIfMissing([], null, tCREGIONAL, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCREGIONAL);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
