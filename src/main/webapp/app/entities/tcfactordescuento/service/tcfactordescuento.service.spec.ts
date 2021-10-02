import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCFACTORDESCUENTO, TCFACTORDESCUENTO } from '../tcfactordescuento.model';

import { TCFACTORDESCUENTOService } from './tcfactordescuento.service';

describe('Service Tests', () => {
  describe('TCFACTORDESCUENTO Service', () => {
    let service: TCFACTORDESCUENTOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCFACTORDESCUENTO;
    let expectedResult: ITCFACTORDESCUENTO | ITCFACTORDESCUENTO[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCFACTORDESCUENTOService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idFactorDescuento: 0,
        factor: 0,
        porcentaje: 0,
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

      it('should create a TCFACTORDESCUENTO', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCFACTORDESCUENTO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCFACTORDESCUENTO', () => {
        const returnedFromService = Object.assign(
          {
            idFactorDescuento: 1,
            factor: 1,
            porcentaje: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCFACTORDESCUENTO', () => {
        const patchObject = Object.assign(
          {
            factor: 1,
          },
          new TCFACTORDESCUENTO()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCFACTORDESCUENTO', () => {
        const returnedFromService = Object.assign(
          {
            idFactorDescuento: 1,
            factor: 1,
            porcentaje: 1,
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

      it('should delete a TCFACTORDESCUENTO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCFACTORDESCUENTOToCollectionIfMissing', () => {
        it('should add a TCFACTORDESCUENTO to an empty array', () => {
          const tCFACTORDESCUENTO: ITCFACTORDESCUENTO = { idFactorDescuento: 123 };
          expectedResult = service.addTCFACTORDESCUENTOToCollectionIfMissing([], tCFACTORDESCUENTO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCFACTORDESCUENTO);
        });

        it('should not add a TCFACTORDESCUENTO to an array that contains it', () => {
          const tCFACTORDESCUENTO: ITCFACTORDESCUENTO = { idFactorDescuento: 123 };
          const tCFACTORDESCUENTOCollection: ITCFACTORDESCUENTO[] = [
            {
              ...tCFACTORDESCUENTO,
            },
            { idFactorDescuento: 456 },
          ];
          expectedResult = service.addTCFACTORDESCUENTOToCollectionIfMissing(tCFACTORDESCUENTOCollection, tCFACTORDESCUENTO);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCFACTORDESCUENTO to an array that doesn't contain it", () => {
          const tCFACTORDESCUENTO: ITCFACTORDESCUENTO = { idFactorDescuento: 123 };
          const tCFACTORDESCUENTOCollection: ITCFACTORDESCUENTO[] = [{ idFactorDescuento: 456 }];
          expectedResult = service.addTCFACTORDESCUENTOToCollectionIfMissing(tCFACTORDESCUENTOCollection, tCFACTORDESCUENTO);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCFACTORDESCUENTO);
        });

        it('should add only unique TCFACTORDESCUENTO to an array', () => {
          const tCFACTORDESCUENTOArray: ITCFACTORDESCUENTO[] = [
            { idFactorDescuento: 123 },
            { idFactorDescuento: 456 },
            { idFactorDescuento: 34214 },
          ];
          const tCFACTORDESCUENTOCollection: ITCFACTORDESCUENTO[] = [{ idFactorDescuento: 123 }];
          expectedResult = service.addTCFACTORDESCUENTOToCollectionIfMissing(tCFACTORDESCUENTOCollection, ...tCFACTORDESCUENTOArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCFACTORDESCUENTO: ITCFACTORDESCUENTO = { idFactorDescuento: 123 };
          const tCFACTORDESCUENTO2: ITCFACTORDESCUENTO = { idFactorDescuento: 456 };
          expectedResult = service.addTCFACTORDESCUENTOToCollectionIfMissing([], tCFACTORDESCUENTO, tCFACTORDESCUENTO2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCFACTORDESCUENTO);
          expect(expectedResult).toContain(tCFACTORDESCUENTO2);
        });

        it('should accept null and undefined values', () => {
          const tCFACTORDESCUENTO: ITCFACTORDESCUENTO = { idFactorDescuento: 123 };
          expectedResult = service.addTCFACTORDESCUENTOToCollectionIfMissing([], null, tCFACTORDESCUENTO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCFACTORDESCUENTO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
