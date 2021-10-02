import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCSUMAASEGURADA, TCSUMAASEGURADA } from '../tcsumaasegurada.model';

import { TCSUMAASEGURADAService } from './tcsumaasegurada.service';

describe('Service Tests', () => {
  describe('TCSUMAASEGURADA Service', () => {
    let service: TCSUMAASEGURADAService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCSUMAASEGURADA;
    let expectedResult: ITCSUMAASEGURADA | ITCSUMAASEGURADA[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCSUMAASEGURADAService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idSumaAsegurada: 0,
        sagff: 0,
        saca: 0,
        sage: 0,
        saiqa: 0,
        saiqv: 0,
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

      it('should create a TCSUMAASEGURADA', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCSUMAASEGURADA()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCSUMAASEGURADA', () => {
        const returnedFromService = Object.assign(
          {
            idSumaAsegurada: 1,
            sagff: 1,
            saca: 1,
            sage: 1,
            saiqa: 1,
            saiqv: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCSUMAASEGURADA', () => {
        const patchObject = Object.assign(
          {
            sage: 1,
            saiqv: 1,
          },
          new TCSUMAASEGURADA()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCSUMAASEGURADA', () => {
        const returnedFromService = Object.assign(
          {
            idSumaAsegurada: 1,
            sagff: 1,
            saca: 1,
            sage: 1,
            saiqa: 1,
            saiqv: 1,
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

      it('should delete a TCSUMAASEGURADA', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCSUMAASEGURADAToCollectionIfMissing', () => {
        it('should add a TCSUMAASEGURADA to an empty array', () => {
          const tCSUMAASEGURADA: ITCSUMAASEGURADA = { idSumaAsegurada: 123 };
          expectedResult = service.addTCSUMAASEGURADAToCollectionIfMissing([], tCSUMAASEGURADA);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCSUMAASEGURADA);
        });

        it('should not add a TCSUMAASEGURADA to an array that contains it', () => {
          const tCSUMAASEGURADA: ITCSUMAASEGURADA = { idSumaAsegurada: 123 };
          const tCSUMAASEGURADACollection: ITCSUMAASEGURADA[] = [
            {
              ...tCSUMAASEGURADA,
            },
            { idSumaAsegurada: 456 },
          ];
          expectedResult = service.addTCSUMAASEGURADAToCollectionIfMissing(tCSUMAASEGURADACollection, tCSUMAASEGURADA);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCSUMAASEGURADA to an array that doesn't contain it", () => {
          const tCSUMAASEGURADA: ITCSUMAASEGURADA = { idSumaAsegurada: 123 };
          const tCSUMAASEGURADACollection: ITCSUMAASEGURADA[] = [{ idSumaAsegurada: 456 }];
          expectedResult = service.addTCSUMAASEGURADAToCollectionIfMissing(tCSUMAASEGURADACollection, tCSUMAASEGURADA);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCSUMAASEGURADA);
        });

        it('should add only unique TCSUMAASEGURADA to an array', () => {
          const tCSUMAASEGURADAArray: ITCSUMAASEGURADA[] = [{ idSumaAsegurada: 123 }, { idSumaAsegurada: 456 }, { idSumaAsegurada: 64594 }];
          const tCSUMAASEGURADACollection: ITCSUMAASEGURADA[] = [{ idSumaAsegurada: 123 }];
          expectedResult = service.addTCSUMAASEGURADAToCollectionIfMissing(tCSUMAASEGURADACollection, ...tCSUMAASEGURADAArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCSUMAASEGURADA: ITCSUMAASEGURADA = { idSumaAsegurada: 123 };
          const tCSUMAASEGURADA2: ITCSUMAASEGURADA = { idSumaAsegurada: 456 };
          expectedResult = service.addTCSUMAASEGURADAToCollectionIfMissing([], tCSUMAASEGURADA, tCSUMAASEGURADA2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCSUMAASEGURADA);
          expect(expectedResult).toContain(tCSUMAASEGURADA2);
        });

        it('should accept null and undefined values', () => {
          const tCSUMAASEGURADA: ITCSUMAASEGURADA = { idSumaAsegurada: 123 };
          expectedResult = service.addTCSUMAASEGURADAToCollectionIfMissing([], null, tCSUMAASEGURADA, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCSUMAASEGURADA);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
