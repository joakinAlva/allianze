import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCSUBGRUPO, TCSUBGRUPO } from '../tcsubgrupo.model';

import { TCSUBGRUPOService } from './tcsubgrupo.service';

describe('Service Tests', () => {
  describe('TCSUBGRUPO Service', () => {
    let service: TCSUBGRUPOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCSUBGRUPO;
    let expectedResult: ITCSUBGRUPO | ITCSUBGRUPO[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCSUBGRUPOService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idSubGrupo: 0,
        subGrupo: 'AAAAAAA',
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

      it('should create a TCSUBGRUPO', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCSUBGRUPO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCSUBGRUPO', () => {
        const returnedFromService = Object.assign(
          {
            idSubGrupo: 1,
            subGrupo: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCSUBGRUPO', () => {
        const patchObject = Object.assign({}, new TCSUBGRUPO());

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCSUBGRUPO', () => {
        const returnedFromService = Object.assign(
          {
            idSubGrupo: 1,
            subGrupo: 'BBBBBB',
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

      it('should delete a TCSUBGRUPO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCSUBGRUPOToCollectionIfMissing', () => {
        it('should add a TCSUBGRUPO to an empty array', () => {
          const tCSUBGRUPO: ITCSUBGRUPO = { idSubGrupo: 123 };
          expectedResult = service.addTCSUBGRUPOToCollectionIfMissing([], tCSUBGRUPO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCSUBGRUPO);
        });

        it('should not add a TCSUBGRUPO to an array that contains it', () => {
          const tCSUBGRUPO: ITCSUBGRUPO = { idSubGrupo: 123 };
          const tCSUBGRUPOCollection: ITCSUBGRUPO[] = [
            {
              ...tCSUBGRUPO,
            },
            { idSubGrupo: 456 },
          ];
          expectedResult = service.addTCSUBGRUPOToCollectionIfMissing(tCSUBGRUPOCollection, tCSUBGRUPO);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCSUBGRUPO to an array that doesn't contain it", () => {
          const tCSUBGRUPO: ITCSUBGRUPO = { idSubGrupo: 123 };
          const tCSUBGRUPOCollection: ITCSUBGRUPO[] = [{ idSubGrupo: 456 }];
          expectedResult = service.addTCSUBGRUPOToCollectionIfMissing(tCSUBGRUPOCollection, tCSUBGRUPO);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCSUBGRUPO);
        });

        it('should add only unique TCSUBGRUPO to an array', () => {
          const tCSUBGRUPOArray: ITCSUBGRUPO[] = [{ idSubGrupo: 123 }, { idSubGrupo: 456 }, { idSubGrupo: 13584 }];
          const tCSUBGRUPOCollection: ITCSUBGRUPO[] = [{ idSubGrupo: 123 }];
          expectedResult = service.addTCSUBGRUPOToCollectionIfMissing(tCSUBGRUPOCollection, ...tCSUBGRUPOArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCSUBGRUPO: ITCSUBGRUPO = { idSubGrupo: 123 };
          const tCSUBGRUPO2: ITCSUBGRUPO = { idSubGrupo: 456 };
          expectedResult = service.addTCSUBGRUPOToCollectionIfMissing([], tCSUBGRUPO, tCSUBGRUPO2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCSUBGRUPO);
          expect(expectedResult).toContain(tCSUBGRUPO2);
        });

        it('should accept null and undefined values', () => {
          const tCSUBGRUPO: ITCSUBGRUPO = { idSubGrupo: 123 };
          expectedResult = service.addTCSUBGRUPOToCollectionIfMissing([], null, tCSUBGRUPO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCSUBGRUPO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
