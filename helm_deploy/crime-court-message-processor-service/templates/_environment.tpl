{{/* vim: set filetype=mustache: */}}
{{/*
Environment variables for service containers
*/}}
{{- define "crime-court-message-processor-service.env-vars" }}
env:
  - name: AWS_REGION
    value: {{ .Values.aws_region }}
  - name: SENTRY_DSN
    valueFrom:
      secretKeyRef:
        name: crime-court-message-processor-service-env-variables
        key: SENTRY_DSN
  - name: SENTRY_ENV
    value: {{ .Values.host_env }}
  - name: SENTRY_SAMPLE_RATE
    value: {{ .Values.sentry.sampleRate | quote }}
  - name: SCOPE_SCHEDULED_TASKS
    value: {{ .Values.scope }}

  - name: DATASOURCE_USERNAME
    valueFrom:
        secretKeyRef:
            name: ccmp-env-variables
            key: TOGDATA_DATASOURCE_USERNAME
  - name: DATASOURCE_PASSWORD
    valueFrom:
        secretKeyRef:
            name: ccmp-env-variables
            key: TOGDATA_DATASOURCE_PASSWORD
  - name: DATASOURCE_URL
    valueFrom:
        secretKeyRef:
            name: ccmp-env-variables
            key: DATASOURCE_URL

{{- end -}}
