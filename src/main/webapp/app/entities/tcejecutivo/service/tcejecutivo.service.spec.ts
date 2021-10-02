import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCEJECUTIVO, TCEJECUTIVO } from '../tcejecutivo.model';

import { TCEJECUTIVOService } from './tcejecutivo.service';

describe('Service Tests', () => {
  describe('TCEJECUTIVO Service', () => {
    let service: TCEJECUTIVOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCEJECUTIVO;
    let expectedResult: ITCEJECUTIVO | ITCEJECUTIVO[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCEJECUTIVOService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idEjecutivo: 0,
        ejecutivo: 'AAAAAAA',
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

      it('should create a TCEJECUTIVO', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCEJECUTIVO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCEJECUTIVO', () => {
        const returnedFromService = Object.assign(
          {
            idEjecutivo: 1,
            ejecutivo: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCEJECUTIVO', () => {
        const patchObject = Object.assign(
          {
            ejecutivo: 'BBBBBB',
          },
          new TCEJECUTIVO()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCEJECUTIVO', () => {
        const returnedFromService = Object.assign(
          {
            idEjecutivo: 1,
            ejecutivo: 'BBBBBB',
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

      it('should delete a TCEJECUTIVO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCEJECUTIVOToCollectionIfMissing', () => {
        it('should add a TCEJECUTIVO to an empty array', () => {
          const tCEJECUTIVO: ITCEJECUTIVO = { idEjecutivo: 123 };
          expectedResult = service.addTCEJECUTIVOToCollectionIfMissing([], tCEJECUTIVO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCEJECUTIVO);
        });

        it('should not add a TCEJECUTIVO to an array that contains it', () => {
          const tCEJECUTIVO: ITCEJECUTIVO = { idEjecutivo: 123 };
          const tCEJECUTIVOCollection: ITCEJECUTIVO[] = [
            {
              ...tCEJECUTIVO,
            },
            { idEjecutivo: 456 },
          ];
          expectedResult = service.addTCEJECUTIVOToCollectionIfMissing(tCEJECUTIVOCollection, tCEJECUTIVO);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCEJECUTIVO to an array that doesn't contain it", () => {
          const tCEJECUTIVO: ITCEJECUTIVO = { idEjecutivo: 123 };
          const tCEJECUTIVOCollection: ITCEJECUTIVO[] = [{ idEjecutivo: 456 }];
          expectedResult = service.addTCEJECUTIVOToCollectionIfMissing(tCEJECUTIVOCollection, tCEJECUTIVO);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCEJECUTIVO);
        });

        it('should add only unique TCEJECUTIVO to an array', () => {
          const tCEJECUTIVOArray: ITCEJECUTIVO[] = [{ idEjecutivo: 123 }, { idEjecutivo: 456 }, { idEjecutivo: 47337 }];
          const tCEJECUTIVOCollection: ITCEJECUTIVO[] = [{ idEjecutivo: 123 }];
          expectedResult = service.addTCEJECUTIVOToCollectionIfMissing(tCEJECUTIVOCollection, ...tCEJECUTIVOArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCEJECUTIVO: ITCEJECUTIVO = { idEjecutivo: 123 };
          const tCEJECUTIVO2: ITCEJECUTIVO = { idEjecutivo: 456 };
          expectedResult = service.addTCEJECUTIVOToCollectionIfMissing([], tCEJECUTIVO, tCEJECUTIVO2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCEJECUTIVO);
          expect(expectedResult).toContain(tCEJECUTIVO2);
        });

        it('should accept null and undefined values', () => {
          const tCEJECUTIVO: ITCEJECUTIVO = { idEjecutivo: 123 };
          expectedResult = service.addTCEJECUTIVOToCollectionIfMissing([], null, tCEJECUTIVO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCEJECUTIVO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
